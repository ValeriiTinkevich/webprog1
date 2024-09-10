$(document).ready(function () {
    $('#submitButton').click(function () {
        // Get the value of x from the text input
        const x = $('#xInput').val();

        // Get the value of y from the checked checkbox
        const y = $('input[name="checkbox"]:checked').val();

        // Get the value of r from the selected radio button
        const r = $('input[name="R"]:checked').val();

        // Check if all inputs are filled
        if (!x || !y || !r) {
            $('#errorMessage').text('Please fill in all fields.').show();
            return;
        } else {
            $('#errorMessage').hide();
        }

        // Define the URL with the parameters
        const url = `/fcgi-bin/hello-world.jar?x=${x}&y=${y}&r=${r}`;

        // Perform the POST request using ajax
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
            },
            error: function () {
                $('#errorMessage').text('Failed to fetch data.').show();
            }
        });
    });
});
