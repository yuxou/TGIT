$(document).ready(
    function () {
        for (var i = 2021; i > 1920; i--) {
            $('#year').append('<option value="' + i + '">' + i + '</option>');
        }
        for (var i = 1; i < 13; i++) {
            $('#month').append('<option value="' + i + '">' + i + '</option>');
        }
        for (var i = 1; i < 32; i++) {
            $('#day').append('<option value="' + i + '">' + i + '</option>');
        }
    }
);