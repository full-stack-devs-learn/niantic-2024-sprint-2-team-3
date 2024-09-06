let questionPage = 1;
let questionTotal = 1;
let pageTotal = 1;
let firstQuestion = true;
let correctTracker=0;

let quizId;
let questionId;
let container;
let button;

document.addEventListener("DOMContentLoaded", () => {
    container = document.getElementById("question-container");
    quizId = document.getElementById("quiz-id").textContent;
    button = document.getElementById("quiz-button");

    // button event
    button.addEventListener("click", () => {
        checkAnswer()
        loadQuestion();
    })

    // change questionTotal based on amount of questions in quiz
    fetch(`/api/quiz/${quizId}/questionCount`)
        .then(response => response.text())
        .then(data => {
            questionTotal = +data;
            pageTotal = questionTotal + 1;
        });
});

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

        // WHY DOES THIS ONLY WORK WITHIN DATA AND NOT OUTSIDE ( why doesnt questionID get updated until second button click)
        loadAnswers();
    })
    questionPage ++;
}

function loadAnswers()
{
     if (questionPage===pageTotal+1) return;
    const url= `/api/quiz/${questionId}/answer`;

    fetch(url)
    .then(response => {return response.json()})
    .then(data =>{

    data.forEach(answer=>displayAnswers(answer,container))

    })


}

function loadResults()
{
    // add a try again button? change button to home page or list of quizzes?
    let title = document.createElement("h4");
    title.textContent = "Results";
    let result = document.createElement("p");
    result.textContent = correctTracker;
    container.appendChild(title);
}

function displayQuestion(data, container)
{
    let question = document.createElement("h4");

    question.textContent = "Question " + data.questionNumber + ": " + data.questionText;

    container.appendChild(question);
}

function displayAnswers(data, container)
{
    let div = document.createElement("div");
    let input = document.createElement("input");
    let label = document.createElement("label");

    input.setAttribute('id', data.answerId.toString());
    input.type = "radio";
    input.name="answer";
    // WHY WAS THIS RENAMED TO CORRECT
    input.value = data.correct.toString();

    label.htmlFor = data.answerId.toString();
    label.textContent = data.answerText;

    div.classList.add("form-check");
    input.classList.add("form-check-input");
    label.classList.add("form-check-label");

    div.appendChild(input);
    div.appendChild(label);

    container.appendChild(div);
}

 function checkAnswer()
 {
   const answers=document.getElementsByName("answer")

   answers.forEach(answer =>
   {
    if(answer.checked && answer.value==="true") correctTracker++;
  })


 }