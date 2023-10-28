# JFit - Documentation

Check out the demo of my project on YouTube:

[![Demo Video](https://img.youtube.com/vi/hmgsP2taL3s/0.jpg)](https://www.youtube.com/watch?v=hmgsP2taL3s)

## Description

JFit is a fitness gamification app that helps you stay motivated and achieve your fitness goals. With JFit, you can earn rewards and badges by completing or creating quests related to your favorite type of exercises.

## Functional Features

### Authentication

- JFit includes a secure authentication system to ensure the protection of user accounts and data. Users must create an account and log in to access the app's features.
- During the registration process, users provide their username and choose a secure password. The password is stored in a database to prevent unauthorized access.
- Once a user logs in, they are presented with the main menu of the app. Additionally, a rule window will appear on the screen, explaining the guidelines and rules for using the app. This is designed to ensure that all users are aware of the proper use of the app and to promote a positive and respectful community.

### Badges

- Badges are a fun way to show off your achievements.
- Users can view their badges in the "Badges" section of the main menu.
- Each badge corresponds to a category of exercise, such as running, weightlifting, or cycling.
- Users can earn badges by creating quests related to a specific category of exercise.
- Users will receive a badge only when another user solves their quest.
- Each badge can be earned only once.

### Quests

- Quests are a fun way to challenge yourself and earn rewards.
- Users can view available quests in the "Quests" section of the main menu.
- Each quest has a description, exercise type, and reward in tokens.
- Users receive tokens after completing a quest based on the reward specified by the quest creator.
- Tokens are deducted from the creator's wallet upon completion of the quest.
- Users can mark a quest as done by selecting it from the list of quests and clicking the "Done" button.
- Users can create a new quest by filling in the fields and clicking the "Add Quest" button.

### Rankings

- Users can view the leaderboard in the "Rankings" section of the main menu.
- The ranking is based on the total number of tokens earned by a user.
- The more quests a user completes, the higher their ranking will be.

### Notifications

- Users receive notifications when they complete a quest or earn new badges.

### Token System

- Every user starts with 1000 tokens.
- Users can earn tokens by completing quests.
- Users can spend tokens by creating quests.
- To earn the Ultimate Zeus Badge, users must either reach 2000 tokens or earn all the other badges.

## Non-Functional Features

- **Usability:** The user interface is intuitive and easy to navigate. The application is responsive and performs well on low-end devices.
- **Security:** User data is stored in a PostgreSQL database with encrypted passwords. The application has a login system to ensure that only registered users can access their data.
- **Reliability:** The application is designed to handle unexpected errors and exceptions. Regular backups are taken to ensure that data is not lost in case of system failure.
- **Performance:** The application is designed to handle a large number of users and quests. The database is optimized for quick access to user data and quest information.

## Technology Applied

- JFit is built using Java and the JavaFX framework, which enables the creation of rich, interactive user interfaces. To enhance the user experience, CSS is used for styling the JavaFX objects in the app, allowing for customization of the app's user interface.
- The app also utilizes object-oriented programming (OOP) principles to ensure code organization and reusability.
- For the database management system, PostgreSQL is used to store user data securely.
- To ensure the app is easily maintainable and scalable, Gradle is used as the build automation tool to manage dependencies and project configurations.

Overall, the technology applied in my app enables a user-friendly, reliable, and secure experience for app users.
