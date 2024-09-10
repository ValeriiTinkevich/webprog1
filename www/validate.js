var input = document.getElementById("xInput");
var errorTxt = document.getElementById("errorMessage");


input.addEventListener("input", event => {
    if(validateText(input.value)) {
        errorTxt.style.visibility = "hidden"
        input.setCustomValidity("");
    } else {
        errorTxt.style.color = "f54c45"
        errorTxt.style.visibility = "visible"
        errorTxt.textContent = "Error"
        input.setCustomValidity("Error");
    }
});

function validateText(x) {
    if(isNaN(x) || x < -5 || x > 3   ) {
        return false;
    } else {
        return true;
    }
}

function checkBoxClick(clickedCheckbox){
    const checkBoxes = document.querySelectorAll('input[name="checkbox"]');
    if(clickedCheckbox.checked){
        checkBoxes.forEach((checkBox) => {
            if(checkBox !== clickedCheckbox){
                checkBox.disabled = true;
            }
        });
    } else{
        checkBoxes.forEach((checkBox) => {
            checkBox.disabled = false;
        });
    }
}

// document.getElementById("submitButton").addEventListener("click", 
    
//     function () {

//     const messageresponse = document.getElementById("response");
//     const xValue = document.getElementById("xInput").value;

//     const yCheckboxes = document.querySelectorAll("input[name='checkbox']:checked");
//     let yValues = Array.from(yCheckboxes).map(checkbox => checkbox.value);

    
//     const yValue = yValues.join(',');

//     // Get the selected radio button value for R
//     const rValue = document.querySelector("input[name='R']:checked")?.value || '';

//     // Construct the query string
//     const queryString = `x=${encodeURIComponent(xValue)}&y=${encodeURIComponent(yValue)}&r=${encodeURIComponent(rValue)}`;

//     // Send the GET request
//     fetch(`/fcgi-bin/hello-world.jar?${queryString}`)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error("Network response was not ok");
//             }
//             return response.text();
//         })
//         .then(data => {
//             const obj = JSON.parse(data); 
//             return obj;

            
//         })
//         .catch(error => {
//             console.error("There was a problem with the fetch operation:", error);
//         });
// });


// $(document).ready(function(){
// 	$(".submitButton").click(function(){
//         const jsonobj = 
// 		$("#customFields").append('<tr><td>1</td><td>2</td><td>3</td><td>true</td></tr>');
// 	});
//     $("#customFields").on('click','.remCF',function(){
//         $(this).parent().parent().remove();
//     });
// });



