# Trivio Quizzlet App

<img src="/images-README/trivio-home.png" width="400">

## Introduction

Trivio is a Quiz Application that allows users to take a quiz that will display their results at the end and also allows
users to create their own quizzes. Each quiz on the homepage tests the user’s knowledge on different topics with
pre-written questions and answers. The goal of the application is to have a user-friendly interface with intuitive
navigation to enhance the user experience.

The web application uses a MVC architecture design. It runs on a website browser and was created in HTML, Java and
Javascript. The frameworks used to build the website application are a Bootstrap CSS framework and a Springboot
ThymeLeaf Java template engine.

This pair programming project was developed by Charletta Harris and Jane Huynh.


## Features

<img src="/images-README/take-quiz.png" width="400">

### Take Quiz Page

* The Take Quiz Page is where users can select a quiz of their choice to test their knowledge on different topics.
* Users are able to answer questions one at a time by selecting an answer with the radio buttons provided.
    * A user will not be able to move on to the next question until an answer is chosen or else a warning message will
      display prompting the user to make a selection.
* Once a user completes the last question, their final results are displayed.

<img src="/images-README/quiz-management.png" width="400">

### Quiz Management

* The Quiz Management page allows users to either add a new quiz or edit an already existing quiz.
    * Adding a quiz requires users to input relevant information about the quiz.
    * Editing an existing quiz allows users to update the quiz by replacing prefilled inputs with new information.
* Both the add and edit functionalities have server-side validation and client-side validation. These validations only
  allow users to submit information when it fulfills the required conditions.
* Existing quizzes are displayed on the page in a table format with three columns that:
    * display the title of the quiz
    * informs the user whether the quiz is live
    * and contains edit quiz buttons.
* Only live quizzes are displayed on the home page for users to test their knowledge.
* Pressing the quiz title will navigate to the selected quiz’s details page.

<img src="/images-README/quiz-details.png" width="400">
### Quiz Details

* The Quiz Details page displays a list of all the questions associated with a particular quiz.
* The page also allows users to either add a new question or edit an already existing question. The functionality is
  similar to quiz management’s add or edit features.

## Instructions

### How To Take A Quiz

* Navigate
    * Press Home on the Navigation Bar
    * Select the Quiz Title you would like to take
* How To Take Quiz
    * Press the Start Button
    * Select an answer for the question displayed and press the Next button
        * If you do not select an answer you will not be able to proceed
    * Once you are at the last question, you can press the results button for you score

### How To Create A New Quiz or Edit An Existing Quiz

* Navigate
    * Press Quizzes on the Navigation Bar
* How to Add a New Quiz
    * Press the Add Quiz button underneath the Quiz Management Heading
    * Enter Quiz Title in input bar
    * If you want the quiz to appear on the homepage to be available for other users to take, press the checkbox to make
      it live
    * Press submit
* How to Edit an Existing Quiz
    * Press the Edit Quiz Button on the same row as the quiz you are intending to edit
    * For any edits, replace the original details pre-filled on the inputs with the new details
    * Press submit

### How to Create New Questions or Edit An Existing Question

* Navigate
    * Press Quizzes on the Navigation Bar
    * In the table, click on the Quiz Title
* How To Add a New Question
    * Press the Add Question button underneath the Quiz Title Heading
    * Enter the required information and select the quiz you want the question to be under
    * Press Submit
* How To Edit an Existing Question
    * Press the Edit Question Button on the same row as the question you are intending to edit
    * For any edits, replace the original details pre-filled on the inputs with the new details
    * Press submit

## Development Process

### Project Management

We created a Trello Board to manage our project tasks and prioritize our goals.

Before each coding session, we would plan out our tasks by priority. We move cards based on our progress through the
below sections;

* Stretch Goals
* Backlog
* To-Do
* In Progress
* Completed

These sections helped break down the project into manageable steps to complete.



<img src="/images-ReadMe/dao-diagram.png" width="400">
<img src="/images-ReadMe/database-diagram.png" width="400">
<img src="/images-ReadMe/webflow-diagram.png" width="400">

### Design
We designed our project web flow, controller and DAO through diagramming before the start of the project for an idea of
how we might go about the coding process. As we went further along the project, we added more to our diagrams based on
any new features that were added.

While designing the project, we really wanted to prioritize functionality and user experience.

## Challenges

### Challenge 1: Pulling From the API to Populate Web Page Fragments

#### What Was the Problem?

We were trying to figure out how to pull data from a simple API in order to populate a web page fragment to display
questions and answers on the Quiz Page. This was a challenge to us, because we just learned about API the day before.
While we were working through this part of the project, we encountered three obstacles.

#### Obstacle 1: Using the API fetch() in order to load questions after pressing start on the start quiz page.

* Steps:
    * First, we revisited the demo code to review the structure of the fetch() function.
    * Then, we followed the structure and replacing certain parts to fit our web page’s needs.
    * Originally we wrote response.text() for the API fetch() because that was the format we were used to from the
      previous day’s exercise where we used fetch() with a HTML fragment rather than an API.
* Solution: An error occurred at this point, because instead of response.text(), we were supposed to write
  response.json().

#### Obstacle 2: Going from the last question to the result page fragment

* Steps:
    * When we pressed next on the last question, it would load a page fragment that contained a null question instead of
      the results page.
    * Because we were dealing with pulling data, we brainstormed the idea that we would have to make a function in the
      API controller to count the amount of questions available in the quiz.
* Solution: We would then use API fetch() to get the question number count so we could create “if” conditions with
  variables to stop pulling questions after the web page count (minus the start page) equaled the amount of questions.

#### Obstacle 3: The third obstacle was getting the answers to display on it’s associated question

* Steps:
    * The first question’s answer would not load for the first question web page fragment but would instead display on
      the second question’s fragment.
    * We first inspected the browser to debug the problem and saw that the API fetch() for the first set of answers
      would return undefined for the first question’s url.
    * The undefined part of the url was the questionId (global variable) which was inserted into the API fetch. After
      some time, we thought that it was probably where we called the function to load the answers with the API.
        * loadAnswer() was originally placed after calling loadQuestion() in the event listener for the button click
          event. We thought that after loading the question it was logical to load the answers afterwards. This was not
          the case.
        * Since the button event listener was placed inside the event listener when the DOM content loaded, we thought
          that it was probably running both functions at the same time. We then moved loadAnswer() to be inside
          loadQuestion() at the end of the code block so that loadAnswer() did not get called until after everything in
          loadQuestion() ran - wanted to make sure questionId received the correct value and was not undefined.
        * That didn’t work as well so we thought that we should move the function to the nested code block where
          questionId’s value becomes bound to the variable.
* Solution: Moving loadAnswer() did work as the questionId would be set at the same scope as the answer fetch url that
  needed the questionId.

### Challenge 2: How to Set Boolean Value to Insert into the Database

#### What was the problem?

We were trying to figure out how to bind a boolean from a user’s decision in add/edit quiz if they wanted the quiz to be
live or not

#### Obstacle 1: Dropdown selections only bind strings as a value defaulting all inputs as false

* Steps:
    * First, we tried having the value set to a boolean value by not wrapping the words in quotation marks. However,
      that still defaulted the value to be false.
    * We researched for other options that may set values to be true or false similar to booleans and discovered
      checkboxes that did exactly that.
* Solution: We changed the dropdown selection to a checkbox.

#### Obstacle 2: We also encountered a problem with the checkbox in which it would cause the webpage to error out.

* Solution: This was resolved by changing our getter method for the related attribute (isLive) to follow SpringBoot’s
  getter function name model.

## Code We Are Proud Of

### Charletta Harris

### Jane Huynh

## Retrospective

### What did you learn from the project?

### Charletta Harris

I learned how more about API's and how to organize data effectively.
I also learned more about flex boxes and how to improve user experience.
Overall this project taught me about the overall value of pair programming.

### Jane Huynh

Pacing and Importance of Breaks

* Frustration can cause one to lose focus on the end goal that would result in a negative feedback loop and possibly
  writing bad code
*

### What would you do differently?

We would enhance the user experience. The project requirements asked for the navigation of the quiz details page to be
linked with the quiz title in the quiz management page. We followed the project requirements as instructed, but if
we had the choice to change this navigation flow we would instead have a button labeled View Questions next to the edit
button.

### What would you do the same?

* How we structured the quiz page for users to go through the questions is very intuitive.
* Making navigation easier for users by having back/cancel buttons so they don not have to go through multiple clicks to
  get back to a certain page.
* Using API and JavaScript to make page fragments over creating several HTML fragments

### If you had more time, what else would you add to the project?

* If we had more time, we would have added the add/edit answer functionality.
* We would have also not allowed white space as an input on client-side validation.





