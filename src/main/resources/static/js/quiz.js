let questionPage = 1;
let questionTotal = 1;
let pageTotal = 1;
let firstQuestion = true;
let correctTracker=0;

let quizId;
let questionId;
let container;
let button;
let submitError;

document.addEventListener("DOMContentLoaded", () => {
    container = document.getElementById("question-container");
    quizId = document.getElementById("quiz-id").textContent;
    button = document.getElementById("quiz-button");
    submitError = document.createElement("p");

    // button event
    button.addEventListener("click", () => {
        if (firstQuestion || checkAnswer())
        {
            loadQuestion();
        }
        else
        {
            if (submitError.parentElement === container)
            {
                container.removeChild(submitError);
            }
            submitError.textContent = "Oh no! Please answer :(";
            submitError.style.color = 'red';
            container.appendChild(submitError);
        }
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

        loadAnswers();
    })
    questionPage ++;
}

function loadAnswers()
{
     if (questionPage === pageTotal + 1) return;
    const url= `/api/quiz/${questionId}/answer`;

    fetch(url)
    .then(response => {return response.json()})
    .then(data =>{

    data.forEach(answer=>displayAnswers(answer,container))

    })


}

function loadResults()
{
    let title = document.createElement("h4");
    let result = document.createElement("p");
    let fraction = document.createElement("p");

    title.textContent = "Results";
    fraction.textContent = correctTracker + '/' + questionTotal;
    result.textContent = "You got " + correctTracker + " correct!";

    container.appendChild(title);
    container.appendChild(fraction);
    container.appendChild(result);

    button.remove()
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
    let chosen = false;
    const answers = document.getElementsByName("answer")

    answers.forEach(answer => {
        if (answer.checked)
        {
            chosen = true;
        }
        if (answer.checked && answer.value==="true")
        {
            correctTracker++;
        }
    })

    return chosen;

 }