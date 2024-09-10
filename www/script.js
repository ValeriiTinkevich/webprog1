
var input = document.getElementById("xInput");
var errorTxt = document.getElementById("errorMessage");
const submitButton = document.getElementById("submitButton");

input.addEventListener("input", event => {
    const isValid = validateText(input.value);

    
    errorTxt.textContent = isValid ? "" : "Invalid input. Please enter a number between -5 and 3.";
    errorTxt.style.display = isValid ? "none" : "block"; 
    input.setCustomValidity(isValid ? "" : "Error");

    submitButton.disabled = !isValid; 
});


function validateText(value) {
    return !isNaN(value) && value >= -5 && value <= 3;
}

function checkBoxClick(clickedCheckbox) {
    const checkBoxes = document.querySelectorAll('input[name="checkbox"]');
    if (clickedCheckbox.checked) {
        checkBoxes.forEach((checkBox) => {
            if (checkBox !== clickedCheckbox) {
                checkBox.disabled = true; 
            }
        });
    } else {
        checkBoxes.forEach((checkBox) => {
            checkBox.disabled = false; 
        });
    }
}

$(document).ready(function () {
    $('#submitButton').click(function () {
        const x = $('#xInput').val();
        const y = $('input[name="checkbox"]:checked').val();
        const r = $('input[name="R"]:checked').val();

        if (!x || !y || !r) {
            $('#errorMessage').text('Please fill in all fields.').css('display', 'block'); 
            return;
        } else {
            $('#errorMessage').hide(); 
        }

        const url = `/fcgi-bin/server.jar?x=${x}&y=${y}&r=${r}`;

        $.ajax({
            url: url,
            type: 'POST',
            success: function (data) {
                const newRow = `<tr>
                    <td>${data.x}</td>
                    <td>${data.y}</td>
                    <td>${data.r}</td>
                    <td>${data.flag}</td>
                </tr>`;

                $('#dataTable tbody').append(newRow);

                $('#latestTime').text("Latest request times");
                $('#scriptTime').text("Script Time: " + data.script_time);
                $('#serverTime').text("Server Time: "+ data.server_time);
            },
            error: function () {
                $('#errorMessage').text('Failed to fetch data.').css('display', 'block');
            }
        });
    });
});


function updateDateTime() {
    const now = new Date();
  
    const options = {
      timeZoneName: 'short' 
    };
  
    const currentDateTime = now.toLocaleString(undefined, options);
  
    document.querySelector('#datetime').textContent = currentDateTime;
  }
  
  setInterval(updateDateTime, 1000);