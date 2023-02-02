<h1 align="center">Drone Fleet</h1>

<h2>Pre-Requesite</h2>
  <ul>
  <li>Install Java 8</li>
  <li>Install Maven</li>
</ul>
  
  <h2>Steps to Run</h2>
  <ul>
  <li>Run: <b>mvn clean install</b></li>
  <li>Run: <b>mvn spring-boot:run</b></li>
</ul>

  <h2>URLs</h2>
  <ul>
  <li>Swagger: <b>http://localhost:8100/musala-soft/swagger-ui.html</b></li>
  <li>H2 Database: <b>http://localhost:8100/musala-soft/h2-console/</b></li>
</ul>

  <h2>Description</h2>
  <p> This application is used to mange drone functionalities such as registering a drone, adding medicine, load medicine etc. And this application will also logged the Drone battery levels. </p>
  
  <h2>Assumptions</h2>
  <ul>
  <li>Drone model cannot be null</li>
  <li>Drone state cannot be null</li>
  <li>Medication can entered to the database</li>
  <li>If drone state <b>IDLE</b> or <b>LOADING</b> you can add medicine to the drone</li>
  <li>Once drone is reached to the max weight limit, it will be turned into LOADED state</li>
  <li>Medication weight when adding medications to the drone cannot be less than 0</li>
  <li>Weight of medication should given in grams (only double value, No strings)</li>
  <li>Get available drones for loading: assuming loading state drones also availble for loading</li>
</ul>
