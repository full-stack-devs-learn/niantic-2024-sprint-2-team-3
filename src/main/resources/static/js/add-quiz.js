
document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("add-quiz")
    const titleInput = document.getElementById("title")

    form.addEventListener("submit", (event) => {
        if(!form.checkValidity())
        {
            event.preventDefault()
            form.classList.add("was-validated")
        }
    })


})