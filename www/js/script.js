function updateDateTime() {
    const now = new Date();

    const options = {
        timeZoneName: 'short'
    };

    const currentDateTime = now.toLocaleString(undefined, options);

    document.querySelector('#datetime').textContent = currentDateTime;
}
updateDateTime();
setInterval(updateDateTime, 1000);


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



$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "/fcgi-bin/server.jar",
        success: function (response) {


            if (response.data && Array.isArray(response.data)) {
                response.data.forEach(item => {
                    const newRow = `<tr>
                                      <td>${item.x}</td>
                                      <td>${item.y}</td>
                                      <td>${item.r}</td>
                                      <td>${item.flag}</td>
                                    </tr>`;
                    $('#dataTable tbody').append(newRow);
                });

                const lastItem = response.data[response.data.length - 1];
                if (response.data.length !== 0) {
                    $('#latestTime').text("Latest request times");
                    $('#scriptTime').text("Script Time: " + lastItem.script_time);
                    $('#serverTime').text("Server Time: " + lastItem.server_time);
                }
            } else {
                $('#errorMessage').text('Unexpected data format.').css('display', 'block');
            }
        },
        error: function () {
            $('#errorMessage').text('Failed to fetch data.').css('display', 'block');
        }
    });



    $('#submitButton').click(function () {
        const x = $('#xInput').val();
        const yValues = [];
        $('input[name="checkbox"]:checked').each(function () {
            yValues.push($(this).val());
        });
        const r = $('input[name="R"]:checked').val();

        if (!x || yValues.length === 0 || !r) {
            $('#errorMessage').text('Please fill in all fields.').css('display', 'block');
            return;
        } else {
            $('#errorMessage').hide();
        }

        // Loop through each y value and send a separate request for each
        yValues.forEach(y => {
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
                    $('#serverTime').text("Server Time: " + data.server_time);
                },
                error: function () {
                    $('#errorMessage').text('Failed to fetch data.').css('display', 'block');
                }
            });
        });
    });
});
