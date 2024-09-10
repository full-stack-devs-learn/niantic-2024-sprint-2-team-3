let questionPage = 1;
let questionTotal = 1;
let pageTotal = 1;
let firstQuestion = true;
let correctTracker = 0;
let startPage = false;
let questionTracker = 1;

let quizId;
let questionId;
let container;
let button;
let submitError;
let form;

document.addEventListener("DOMContentLoaded", () => {
    container = document.getElementById("question-container");
    quizId = document.getElementById("quiz-id").textContent;
    button = document.getElementById("quiz-button");
    submitError = document.createElement("p");

    container.classList.add("card");
    container.classList.add("bg-info");
    container.classList.add("text-white");
    container.classList.add("mb-3");
    container.classList.add("mt-3");
    container.style.width = "50vw";
    container.style.padding = "25px";

    // change questionTotal based on amount of questions in quiz
        fetch(`/api/quiz/${quizId}/questionCount`)
            .then(response => response.text())
            .then(data => {
                questionTotal = +data;
                pageTotal = questionTotal + 1;

                loadStart();
            });

    // button event
    button.addEventListener("click", () => {
        if (firstQuestion || checkAnswer())
        {
            loadQuestion();
        }
        else if (startPage || questionPage === 1)
        {
            loadStart();
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
        questionTracker++;

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
        let div = document.createElement("div");
        form = document.createElement("div");
        div.classList.add("d-flex");
        div.classList.add("justify-content-center");

        data.forEach(answer => displayAnswers(answer, container));
        div.appendChild(form);
        container.appendChild(div);
    })
}

function loadStart()
{
    startPage = false;
    firstQuestion = true;
    questionPage = 1;
    correctTracker = 0;
    questionTracker = 1;

    container.innerHTML = "";
    let instruction = document.createElement("p");
    let numberQ = document.createElement("p");

    numberQ.textContent = "Number of Questions: " + questionTotal;
    instruction.textContent = "Once you select an answer and press Next, you are unable to change your answer. Think carefully before you go to the next question!";
    button.textContent = "Start";

    numberQ.style.fontSize = "22px";
    instruction.style.fontSize = "20px";

    numberQ.classList.add("text-warning");
    numberQ.classList.add("mt-4");
    instruction.classList.add("mb-5");

    container.appendChild(numberQ);
    container.appendChild(instruction);
}

function loadResults()
{
    let title = document.createElement("h4");
    let result = document.createElement("p");
    let fraction = document.createElement("h3");

    title.textContent = "Results";
    fraction.textContent = correctTracker + '/' + questionTotal;
    result.textContent = "You got " + correctTracker + " correct!";

    fraction.style.color = '#17a2b8';
    fraction.style.fontSize = "84px";

    title.classList.add("mt-4");
    fraction.classList.add("mt-4");
    fraction.classList.add("mb-4");
    fraction.classList.add("text-warning");
    result.classList.add("mb-4");

    container.appendChild(title);
    container.appendChild(fraction);
    container.appendChild(result);

    button.textContent = "Retake Quiz";

    startPage = true;
}

function displayQuestion(data, container)
{
    let numQuestHeader = document.createElement("div");
    let question = document.createElement("h4");
    numQuestHeader.textContent = "Question " + questionTracker + " / " + questionTotal;
    numQuestHeader.style.textAlign = "center";
    numQuestHeader.style.color = '#000000';
    numQuestHeader.style.backgroundColor = '#17a2b8';

    question.textContent = data.questionText;
    question.style.fontSize = "28px";

    numQuestHeader.classList.add("card-header");
    question.classList.add("mt-5");

    container.appendChild(numQuestHeader);
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
    label.style.fontSize = "19px";


    form.classList.add("mb-4");
    form.classList.add("mt-3");
    div.classList.add("form-check");
    div.classList.add("mb-3");
    div.classList.add("answer-choice");
    input.classList.add("form-check-input");
    label.classList.add("form-check-label");

    div.appendChild(input);
    div.appendChild(label);
    form.appendChild(div);
}

 function checkAnswer()
 {
    let chosen = false;
    const answers = document.getElementsByName("answer");

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