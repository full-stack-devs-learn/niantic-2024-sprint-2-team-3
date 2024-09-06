let questionPage = 1;
let questionTotal = 1;
let pageTotal = 1;
let firstQuestion = true;
let quizId;

let button;

document.addEventListener("DOMContentLoaded", () => {
    // on load
    quizId = document.getElementById("quiz-id").textContent;
    button = document.getElementById("quiz-button");

    // when button is 'START'
    button.addEventListener("click", () => {
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
    // if this is the first question, change button text
    if (firstQuestion)
    {
        button.textContent = "Next";
        firstQuestion = false;
    }

    // if this is the last question, change button text
    if (questionPage === pageTotal)
    {
        button.textContent = "Results";
        // maybe add function here for displaying final result after submitting instead of changing button
    }

    const url = `/api/quiz/${quizId}/${questionPage}`;

    fetch(url)
    .then(response => {return response.json()})
    .then(data => {
        let container = document.getElementById("question-container");
        container.innerHTML = '';

        displayQuestion(data, container);
    })
}

function displayQuestion(data, container)
{
    let text = document.createElement("h4");
    let questionNum = document.createElement("h4");

    text.textContent = data.questionText;
    questionNum.textContent = data.questionNumber;

    container.appendChild(questionNum);
    container.appendChild(text);
}