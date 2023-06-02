var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function(){
  var matchNum = /[0-9]/ ;
  var elmAge   = document.getElementById("ID_AGE");
  var elmMessage = document.getElementById("ID_MESSAGE");
  var canSubmit = true;
  if(elmAge.value == "" || elmAge.value.match(matchNum) == false || elmMessage.value == ""){
    alert("不正な入力項目があります。");
    canSubmit = false;
  }
  
  return canSubmit;
}
