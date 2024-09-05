let questionPage = 1;
let questionTotal = 1;

document.addEventListener("DOMContentLoaded", () => {
    // on load
    const quizId = document.getElementById("quiz-id").textContent;
    const button = document.getElementById("quiz-button");

    button.addEventListener("click", () => {

    })

    // change questionTotal based on amount of questions in quiz
    fetch(`/api/quiz/${quizId}/questionCount`)
        .then(response => response.text())
        .then(data => {
            questionTotal = +data;
        });
});