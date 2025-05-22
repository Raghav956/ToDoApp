package com.app.todo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.todo.models.Task;

@Service
public class TaskSummaryService {

	
	private final WebClient webClient;
	 private final String slackWebhookUrl;
	
	public TaskSummaryService(@Value("${cohere.api.key}") String apiKey,@Value("${slack.webhook.url}") String slackWebhookUrl)
	{	        this.slackWebhookUrl = slackWebhookUrl;

        this.webClient = WebClient.builder()
                .baseUrl("https://api.cohere.ai/v1/chat")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
	}
	public String summarizeTasks(List<Task> tasks) {
		
		   if (tasks.isEmpty()) {
		        return "No tasks to summarize.";
		    }
		   
		StringBuilder prompt = new StringBuilder("Here is the task list to summarize :\n");
		 for (Task task : tasks) {
	            prompt.append("Title: ").append(task.getTitle()).append("\n")
	                  .append("Description: ").append(task.getDescription()).append("\n");
	        }
		
		  Map<String, Object> requestBody = Map.of(
	                "model", "command",  
	                "message", prompt.toString(),
	                "stream", false
	        );
		  Map response = webClient.post()
	                .bodyValue(requestBody)
	                .retrieve()
	                .bodyToMono(Map.class)
	                .block();
		  
		  String summary = (response != null && response.containsKey("text"))
	                ? response.get("text").toString()
	                : response != null && response.containsKey("response")
	                    ? response.get("response").toString()
	                    : "Failed to get summary from Cohere.";
		  sendToSlack(summary); 
	        return summary;
	}
	public String sendToSlack(String message) {
	    try {
	        WebClient.create()
	            .post()
	            .uri(slackWebhookUrl)
	            .header("Content-Type", "application/json")
	            .bodyValue(Map.of("text", message))
	            .retrieve()
	            .bodyToMono(String.class)
	            .block(); 

	        return "Success"; 
	    } catch (Exception e) {
	        return "Failure"; 
	    }
	}
}
