$(document).ready(function () {
    $('#play-mp3').click(function () {
        Android.playMp3();
    });

    $('#play-mp4').click(function () {
        Android.playMp4();
    });

    // Gửi OTP từ Java
    Android.generateOtp(function (otp) {
        $('#output').text('Mã OTP là: ' + otp);
    });
});
