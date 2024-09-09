# Trivio Quizzlet App

<img src="/images-README/trivio-home.png" width="400">

## Table of Contents

1. [Introduction](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#introduction)
2. [Features](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#features)
3. [Instructions](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#instructions)
4. [Development Process](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#development-process)
5. [Challenges](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#challenges)
6. [Codes We Are Proud Of](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#code-we-are-proud-of)
7. [Retrospective](https://github.com/full-stack-devs-learn/niantic-2024-sprint-2-team-3/blob/main/README.md#retrospective)

## Introduction

Trivio is a Quiz Application that allows users to take a quiz that will display their results at the end and also allows
users to create their own quizzes. Each quiz on the homepage tests the user’s knowledge on different topics with
pre-written questions and answers. The goal of the application is to have a user-friendly interface with intuitive
navigation to enhance the user experience.

The web application was built with a Model-View-Controller (MVC) architecture design and was created in HTML, Java and
Javascript. Controllers and DAOs were written in Java for the backend in order to pull data from a database into
the different webpages. HTML was used to build the structure of the webpage and Javascript was used to populate the DOM with webpage
fragments that pulled data from a simple API. The frameworks applied to build the website application are a Bootstrap CSS framework and a Springboot
ThymeLeaf Java template engine. Bootstrap was used to have a mobile-first design with the ability to adjust to many
different device sizes and Springboot Thymeleaf was used to build user-friendly templates for the frontend.

This pair programming project was developed by Charletta Harris and Jane Huynh.

## Features

### Take Quiz

<img src="/images-README/take-quiz.png" width="800">

* The Take Quiz Page is where users can select a quiz of their choice to test their knowledge on different topics.
* Users are able to answer questions one at a time by selecting an answer with the radio buttons provided.
    * A user will not be able to move on to the next question until an answer is chosen or else a warning message will
      display prompting the user to make a selection.
* Once a user completes the last question, their final results are displayed.

### Quiz Management

<img src="/images-README/quiz-management.png" width="800">

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

### Quiz Details

<img src="/images-README/quiz-details.png" width="800">

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
    * In the table, click on the Quiz Title or press the View Questions button
* How To Add a New Question
    * Press the Add Question button underneath the Quiz Title Heading
    * Enter the required information and select the quiz you want the question to be under
    * Press Submit
* How To Edit an Existing Question
    * Press the Edit Question Button on the same row as the question you are intending to edit
    * For any edits, replace the original details pre-filled on the inputs with the new details
    * Press submit

### How to View Answers for a Question

* Navigate
    * Press Quizzes on the Navigation Bar
    * In the table, click on the Question Text or press the View Answers button

## Development Process

### Project Management

<img src="/images-README/trello.png" width="800">

We created a Trello Board to manage our project tasks and prioritize our goals. It helped us visually on what needed to
be done and keep us accountable.

Before each coding session, we would plan out our tasks by priority. We move cards based on our progress through the
below sections:

* Stretch Goals
* Backlog
* To-Do
* In Progress
* Completed

These sections helped break down the project into manageable steps to complete.

### Design

We designed our project web flow, controller and DAO through diagramming before the start of the project for an idea of
how we might go about the coding process. The brainstorming we did was to make sure we really understood the MVC design
pattern and how every component or part worked with each other before beginning any code. We made sure to reference the
diagrams as we coded and made changes whenever
we saw a need for new features or methods.

### Database Tables (Relational)

How each database table relates to each other.

<img src="/images-README/database-diagram.png" width="700"><br>

### Models

We laid out what each object is and what data it contains.

<img src="/images-README/model-diagram.png" width="700">

### DAO

DAO methods were thought out on how to best pull data from the database to be used by the controllers alongside updating
the database when users desired to add or edit existing quizzes.

<img src="/images-README/dao-diagram.png" width="700"><br>

### Webflow

While designing the project, we really wanted to prioritize functionality and user experience so we kept this in mind
while designing webflow or the navigation of pages. Color codding of quizzes, quiz details, and question details is
similar to above with main purpose in mind.

<img src="/images-README/webflow-diagram.png" width="700">

## Challenges

### Challenge 1: Pulling From the API to Populate Web Page Fragments

#### What Was the Problem?

We were trying to figure out how to pull data from a simple API in order to populate a web page fragment to display
questions and answers on the Quiz Page. This was a challenge to us, because we just learned about API the day before.
While we were working through this part of the project, we encountered three obstacles.

#### Obstacle 1: Using the API fetch() in order to load questions after pressing start on the start quiz page.

* Steps:
    * First, we revisited the demo code to review the structure of the ```fetch()``` function.
    * Then, we followed the structure and replacing certain parts to fit our web page’s needs.
    * Originally we wrote response.text() for the API ```fetch()``` because that was the format we were used to from the
      previous day’s exercise where we used fetch() with a HTML fragment rather than an API.
* Solution: An error occurred at this point, because instead of response.text(), we were supposed to write
  response.json().
* Note: For each new "feature" of any API call, we would test it out in the browser before continuing on to the next
  one.

#### Obstacle 2: Going from the last question to the result page fragment

* Steps:
    * When we pressed next on the last question, it would load a page fragment that contained a null question instead of
      the results page.
    * Because we were dealing with pulling data, we brainstormed the idea that we would have to make a function in the
      API controller called ```getQuestionCount()``` to count the amount of questions available in the quiz.
* Solution: We would then use API ```fetch()``` to get the question number count in order to create “if” conditions with
  variables to stop pulling questions after the web page count (minus the start page) equaled the amount of questions.

#### Obstacle 3: The third obstacle was getting the answers to display on it’s associated question

* Steps:
    * The first question’s answer would not load for the first question web page fragment but would instead display on
      the second question’s fragment.
    * We first inspected the webpage to debug the problem using the browser console and saw that the API ```fetch()``` for the
      first set of answers
      would return undefined for the first question’s url.
    * The undefined part of the url was the questionId (global variable) which was inserted into the API fetch. After
      some time of going through the bug with debugger several times, we thought that it was probably where we called
      the function to load the answers with the API.
        * ```loadAnswer()``` was originally placed after calling ```loadQuestion()``` in the event listener for the button click
          event. We thought that after everything in loadQuestion() was completed, it was logical to call ```loadAnswer()```
          in the next line of the eventListener. This was not
          the case.
        * Since the button click ```eventListener()``` was placed inside the DOM content ```loaded eventListener()```, we thought
          that it was probably running both functions at the same time. We then moved ```loadAnswer()``` to be inside
          ```loadQuestion()``` at the end of the code block so that ```loadAnswer()``` did not get called until after everything in
          ```loadQuestion()``` ran. We wanted to make sure questionId received the correct value and was not undefined.
        * That did not work as well. We then thought that we should move the function to the nested code block where
          questionId’s value becomes bound to the variable.
* Solution: Moving ```loadAnswer()``` did work as the questionId would be set at the same scope as the answer fetch url that
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

* Steps:
    * Combed through our code to see any misspells or wrong variable names after receiving the error message.
* Solution: This was resolved by changing our getter method for the related attribute (isLive) to follow the
  conventional naming model of the
  getter function.

## Code We Are Proud Of

### Charletta Harris
I am really proud of the code block below. It pulls data using the DAO and the best part is
seeing it displayed on the webpage.This code helped structure the page, and I had to use a
QuestionDAO with a GetMapping to make it all work. 


```html
<table class="table table-hover">
        <thead class="table-dark">
            <th>Number</th>
            <th>Question</th>
            <th></th>
        </thead>
        <tbody class="table-light">
        <tr th:each="question:${questions}">
            <td th:text="${question.questionNumber}"></td>
            <th><a th:href="@{|/quiz/${quiz.quizId}/${question.questionId}/details|}" th:text="${question.questionText}"></a></th>
            <td style="text-align: right;">
                <a th:href="@{|/quiz/${quiz.quizId}/${question.questionId}/details|}" class="btn btn-info">View Answers</a>
                <a th:href="@{|/quiz/${quiz.quizId}/${question.questionId}/edit|}" class="btn btn-warning">Edit Question</a>
            </td>
        </tr>
        </tbody>

    </table>
```

### Jane Huynh

The following block of code is the function that loads the questions of the quiz into individual web page fragments
based on the user’s progress. It calls the QuizPageAPIController to pull data using a method in the QuestionDAO to grab
information from the trivio database. I'm really proud of this block of code, because it became the basis of our Take
Quiz
webpage fragments by setting conditions to either load questions, load answers or load the results page.

```javascript
function loadQuestion()
{
    // if this is the first question, change button text from start to next
    if (firstQuestion)
    {
        button.textContent = "Next";
        firstQuestion = false;
    }

    // after last question, load results fragment instead
    if (questionPage === pageTotal)
    {
        container.innerHTML = "";
        loadResults();
        return;
    }

    // if this is the last question, change button text & prepare to load results fragment
    if (questionPage === pageTotal - 1)
    {
        button.textContent = "Results";
    }

    // load question fragments
    const url = `/api/quiz/${quizId}/${questionPage}`;

    fetch(url)
    .then(response => {return response.json()})
    .then(data => {
        container.innerHTML = '';
        questionId = data.questionId;

        displayQuestion(data, container);

        loadAnswers();
    })
    questionPage ++;
}
```

## Retrospective

### What did you learn from the project?

#### Charletta Harris

* Gained a deeper understanding of how API's work including how to request and retrieve data. 
* Flexbox and how to improve the overall user experience by optimizing the visual structure to make it intuitive.
* Overall this project taught me about the overall value of pair programming.


#### Jane Huynh

* Pacing and Importance of Breaks
    * Frustration can cause one to lose focus on the endgoal that would result in a negative feedback loop and possibly
      start writing bad or unnecessary code.
    * Therefore, pulling back and working on other features or taking breaks in between sessions is important in order
      to reset your mentality
* Advantages of API & Flexibility of Javascript
    * With API, there are less files to keep track of and manage. We can have a single javascript file with API as
      opposed to multiple HTML fragments with just Thymeleaf.

### What would you do differently?

* Reworking Priorities
    * For every project requirement done, work on ReadMe and brainstorm ideas to improve on user interface or
      functionality instead of doing 2 requirements at a time first
* Enhance User Experience more
    * Display Quiz Management differently to differentiate from Quiz Details / Question Details page
    * Change how to turn quiz live from a checkbox to maybe a more noticeable input / selection type that still binds to
      a boolean

### What would you do the same?

* How we structured the quiz page for users to go through the questions is very intuitive.
* Making navigation easier for users by having back to previous page or cancel buttons so users do not have to go
  through multiple clicks to
  get back to a certain page.
* Using API and JavaScript to make page fragments over creating several HTML fragments
* Pair programming to help encourage one another and reminders to not hyper focus on a problem

### If you had more time, what else would you add to the project?

* Worked more on the aesthetics and design
    * Add more shapes and images to brighten up webpage
    * Make a custom page and not rely on a bootstrap framework
    * Create a more solid theme and color scheme
* Add the add/edit answer functionality.
* Not allow white space as an input on client-side validation (server-side already checks this).
* Immediate feedback that tells users if their answer was correct or not after submitting
* Automatically disable a quiz if users added a question with no answer(s)
* Have different types of answers included in the quiz (e.g. not just radio buttons but also checkboxes or input text)





