
function setClientTime() {
    var currentTime = new Date();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var seconds = currentTime.getSeconds();
    if (minutes < 10)
        minutes = '0' + minutes;
    if (seconds < 10)
        seconds = '0' + seconds;
    document.getElementById('ClientTime').innerHTML = hours + ':' + minutes + ':' + seconds;
}

function setServerTime(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status == 200) {
            document.getElementById('ServerTime').innerHTML = JSON.parse(this.responseText).time;
        } else {
            alert("Server response: " + this.status);
        }
    };

    xhttp.open("GET", "/server-time", true);
    xhttp.send();
}

function refresh() {
    setClientTime();
    setServerTime();
}