<!DOCTYPE html>
<html>
<body>
	<h1>JFit - documentation</h1>
	<h2>Check out the demo of my project on YouTube:
	[![Demo Video]](https://www.youtube.com/watch?v=hmgsP2taL3s)</h2>
	<h2>Description</h2>
	<p>JFit is a fitness gamification app that helps you stay motivated and achieve your fitness goals. With JFit, you can earn rewards and badges by completing or creating quests related to your favorite type of exercises.</p>
<div>
  <h2>Functional Features</h2>
	<h3>Authentication</h3>
	<li>JFit includes a secure authentication system to ensure the protection of user accounts and data. Users must create an account and log in to access the app's features.</li>
	<li>During the registration process, users provide their username and choose a secure password. The password is stored in a database to prevent unauthorized access.</li>
	<li>Once a user logs in, they are presented with the main menu of the app. Additionally, a rule window will appear on the screen, explaining the guidelines and rules for using the app. This is designed to ensure that all users are aware of the proper use of the app and to promote a positive and respectful community.</li>
	
  <h3>Badges</h3>
  <ul>
    <li>Badges are a fun way to show off your achievements.</li>
    <li>Users can view their badges in the "Badges" section of the main menu.</li>
    <li>Each badge corresponds to a category of exercise, such as running, weightlifting, or cycling.</li>
    <li>Users can earn badges by creating quests related to a specific category of exercise.</li>
    <li>Users will receive a badge only when another user solves their quest.</li>
    <li>Each badge can be earned only once.</li>
  </ul>
  <h3>Quests</h3>
  <ul>
    <li>Quests are a fun way to challenge yourself and earn rewards.</li>
    <li>Users can view available quests in the "Quests" section of the main menu.</li>
    <li>Each quest has a description, exercise type, and reward in tokens.</li>
    <li>Users receive tokens after completing a quest based on the reward specified by the quest creator.</li>
    <li>Tokens are deducted from the creator's wallet upon completion of the quest.</li>
    <li>Users can mark a quest as done by selecting it from the list of quests and clicking the "Done" button.</li>
    <li>Users can create a new quest by filling in the fields and clicking the "Add Quest" button.</li>
  </ul>
  <h3>Rankings</h3>
  <ul>
    <li>Users can view the leaderboard in the "Rankings" section of the main menu.</li>
    <li>The ranking is based on the total number of tokens earned by a user.</li>
    <li>The more quests a user completes, the higher their ranking will be.</li>
  </ul>
  <h3>Notifications</h3>
  <ul>
    <li>Users receive notifications when they complete a quest or earn new badges.</li>
  </ul>
  <h3>Token System</h3>
  <ul>
    <li>Every user starts with 1000 tokens.</li>
    <li>Users can earn tokens by completing quests.</li>
    <li>Users can spend tokens by creating quests.</li>
    <li>To earn the Ultimate Zeus Badge, users must either reach 2000 tokens or earn all the other badges.</li>
  </ul>
</div>

<div>
  <h2>Non-Functional Features</h2>
  <ul>
    <li><strong>Usability:</strong> The user interface is intuitive and easy to navigate. The application is responsive and performs well on low-end devices.</li>
    <li><strong>Security:</strong> User data is stored in a PostgreSQL database with encrypted passwords. The application has a login system to ensure that only registered users can access their data.</li>
    <li><strong>Reliability:</strong> The application is designed to handle unexpected errors and exceptions. Regular backups are taken to ensure that data is not lost in case of system failure.</li>
    <li><strong>Performance:</strong> The application is designed to handle a large number of users and quests. The database is optimized for quick access to user data and quest information.</li>
  </ul>
</div>
<div>
	<h2>Technology Applied</h2>
	<ul>
		<li>JFit is built using Java and the JavaFX framework, which enables the creation of rich, interactive user interfaces. To enhance the user experience, I use CSS for styling the JavaFX objects in the app. This allows me to customize the appearance of the app's user interface and create a visually appealing experience for the users.</li>
		<li>The app also utilizes object-oriented programming (OOP) principles to ensure code organization and reusability.</li>
		<li>For the database management system, I use PostgreSQL to store user data securely. PostgreSQL is a widely used and trusted open-source relational database management system that is known for its reliability, scalability, and performance.</li>
		<li>To ensure the app is easily maintainable and scalable, I use Gradle as the build automation tool to manage dependencies and project configurations. This allows us to easily update and manage the app's dependencies and configurations in a standardized and efficient way.</li>
		<li>Overall, the technology applied in my app enables a user-friendly, reliable, and secure experience for app's users.</li>
	</ul>
</div>	
</body>
</html>
