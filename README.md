- Add, edit, and delete tasks
- View all tasks
- Summarize all tasks with a single button
- Send the task summary to a Slack channel via webhook

- Java 17+
- React
- SuperBase
- A Slack workspace and a Webhook URL

Backend runs on: http://localhost:8080
Frontend runs on: http://localhost:3000


Setup Instructions
1. Clone the Repository
2.Slack Webhook Setup
To allow the app to send task summaries to your Slack workspace:
	1.	Create a Slack App with Incoming Webhooks enabled.
	2.	Install the app to your workspace.
	3.	Copy the generated Webhook URL.
  4.	paste the url in the application.properties in the slack.webhook.url


Task has 4 attributes
1. Id
2. title
3. description
4. status


For LLM Integration -Cohere using spring boot web to get the response
