window.onload = function(){
 document.getElementById("InputURL").value = "";
 document.getElementById("InputTaskId").value = "";
};

function handleChange() {
    var x = document.getElementById("InputURL").value;
    console.log(x);
    if (x.length > 490)
    {
        document.getElementById("errorMsg").style.display = "block";
        document.getElementById("submit-button").disabled = true;
        document.getElementById("taskidshow").style.display = "none";
    }
    else {
            document.getElementById("errorMsg").style.display = "none";
            document.getElementById("submit-button").disabled = false;
    }

  }