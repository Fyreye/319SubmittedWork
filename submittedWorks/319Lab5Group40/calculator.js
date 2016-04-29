// CALCULATOR.JS
//
//
//

// 
var Calc = {

Model : {
},

View : {
  mem : {id: "mem", type: "text", value:"", onclick:""},
  textRow : {id: "textRow", type: "text", value: "", onclick:""},
  button1 : {id: "button1", type: "button", value: "  1  ", onclick:""},
  button2 : {id: "button2", type: "button", value: "  2  ", onclick:""},
  button3 : {id: "button3", type: "button", value: "  3  ", onclick:""},
  button4 : {id: "button4", type: "button", value: "  4  ", onclick:""},
  button5 : {id: "button5", type: "button", value: "  5  ", onclick:""},
  button6 : {id: "button6", type: "button", value: "  6  ", onclick:""},
  button7 : {id: "button7", type: "button", value: "  7  ", onclick:""},
  button8 : {id: "button8", type: "button", value: "  8  ", onclick:""},
  button9 : {id: "button9", type: "button", value: "  9  ", onclick:""},
  button0 : {id: "button0", type: "button", value: "  0  ", onclick:""},
  buttonPlus : {id: "button0", type: "button", value: '  + ', onclick:""},
  buttonMin : {id: "button1", type: "button", value: '  -  ', onclick:""},
  buttonMult : {id: "button2", type: "button", value: '  * ', onclick:""},
  buttonEq : {id: "button3", type: "button", value: '  = ', onclick:""},
  buttonDiv : {id: "button4", type: "button", value: '  /  ', onclick:""},
  buttonDot : {id: "button5", type: "button", value: '   .  ', onclick:""},
  buttonC : {id: "button6", type: "button", value: '  C ', onclick:""},
  buttonMR : {id: "button7", type: "button", value: "MR", onclick:""},
  buttonMC : {id: "button8", type: "button", value: "MC", onclick:""},
  buttonMS : {id: "button7", type: "button", value: "MS", onclick:""},
  buttonMminus : {id: "button8", type: "button", value: "M- ", onclick:""},
  buttonMplus : {id: "button9", type: "button", value: "M+", onclick:""}
},

Controller : {

},

run : function() {
  Calc.attachHandlers();
  console.log(Calc.display());
  return Calc.display();
},


displayElement : function (element) {
  var s = "<input ";
  s += " id=\"" + element.id + "\"";
  s += " type=\"" + element.type + "\"";
  s += " value= \"" + element.value + "\"";
  s += " onclick= \"" + element.onclick + "\"";
  s += ">";
  return s;

},

display : function() {
  var s;
  s = "<table id=\"myTable\" border=0>"
  s += Calc.displayElement(Calc.View.textRow);
  s += "<tr><td>";
  s += Calc.displayElement(Calc.View.button7);
  s +=  Calc.displayElement(Calc.View.button8);
  s += Calc.displayElement(Calc.View.button9);
  s += Calc.displayElement(Calc.View.buttonPlus);
  s += "<br>";
  s += Calc.displayElement(Calc.View.button4);
  s += Calc.displayElement(Calc.View.button5);
  s += Calc.displayElement(Calc.View.button6);
  s += Calc.displayElement(Calc.View.buttonMin);
  s += "<br>";
  s += Calc.displayElement(Calc.View.button1);
  s += Calc.displayElement(Calc.View.button2);
  s += Calc.displayElement(Calc.View.button3);
  s += Calc.displayElement(Calc.View.buttonMult);
  s += "<br>";
  s += Calc.displayElement(Calc.View.button0);
  s += Calc.displayElement(Calc.View.buttonDot);
  s += Calc.displayElement(Calc.View.buttonEq);
  s += Calc.displayElement(Calc.View.buttonDiv);
  s += "<br>";
  s += Calc.displayElement(Calc.View.buttonC);
  s += Calc.displayElement(Calc.View.buttonMR);
  s += Calc.displayElement(Calc.View.buttonMS);
  s += Calc.displayElement(Calc.View.buttonMC);
  s += Calc.displayElement(Calc.View.buttonMminus);
  s += Calc.displayElement(Calc.View.buttonMplus);
  
  s += "</tr></td></table>";
  return s;
},

attachHandlers : function() 
{
  Calc.View.button1.onclick = "Calc.button1Handler()"; 
  Calc.View.button2.onclick = "Calc.button2Handler()";
  Calc.View.button3.onclick = "Calc.button3Handler()"; 
  Calc.View.button4.onclick = "Calc.button4Handler()";
  Calc.View.button5.onclick = "Calc.button5Handler()"; 
  Calc.View.button6.onclick = "Calc.button6Handler()";
  Calc.View.button7.onclick = "Calc.button7Handler()"; 
  Calc.View.button8.onclick = "Calc.button8Handler()";
  Calc.View.button9.onclick = "Calc.button9Handler()"; 
  Calc.View.button0.onclick = "Calc.button0Handler()";
  
  Calc.View.buttonPlus.onclick = "Calc.buttonPlusHandler()"; 
  Calc.View.buttonMin.onclick = "Calc.buttonMinHandler()";
  Calc.View.buttonMult.onclick = "Calc.buttonMultHandler()"; 
  Calc.View.buttonEq.onclick = "Calc.buttonEqHandler()";
  Calc.View.buttonDiv.onclick = "Calc.buttonDivHandler()"; 
  Calc.View.buttonDot.onclick = "Calc.buttonDotHandler()";
  Calc.View.buttonC.onclick = "Calc.buttonCHandler()"; 
  Calc.View.buttonMR.onclick = "Calc.memButtonHandlers().buttonMRHandler()";
  Calc.View.buttonMC.onclick = "Calc.memButtonHandlers().buttonMCHandler()"; 
  Calc.View.buttonMS.onclick = "Calc.memButtonHandlers().buttonMSHandler()";
  Calc.View.buttonMminus.onclick = "Calc.memButtonHandlers().buttonMminusHandler()"; 
  Calc.View.buttonMplus.onclick = "Calc.memButtonHandlers().buttonMplusHandler()";
  
},

button1Handler : function() {
  document.getElementById("textRow").value += '1';
},
button2Handler : function() {
  document.getElementById("textRow").value += '2';
},
button3Handler : function() {
  document.getElementById("textRow").value += '3';
},
button4Handler : function() {
  document.getElementById("textRow").value += '4';
},
button5Handler : function() {
  document.getElementById("textRow").value += '5';
},
button6Handler : function() {
  document.getElementById("textRow").value += '6';
},
button7Handler : function() {
  document.getElementById("textRow").value += '7';
},
button8Handler : function() {
  document.getElementById("textRow").value += '8';
},
button9Handler : function() {
  document.getElementById("textRow").value += '9';
},
button0Handler : function() {
  document.getElementById("textRow").value += '0';
},

//Opperator Handlers
buttonPlusHandler : function() {
  document.getElementById("textRow").value += ' + ';
},
buttonMinHandler : function() {
  document.getElementById("textRow").value += ' - ';
},
buttonMultHandler : function() {
  document.getElementById("textRow").value += ' * ';
},
buttonEqHandler : function() {
  document.getElementById("textRow").value = eval(document.getElementById("textRow").value); 
},
buttonDivHandler : function() {
  document.getElementById("textRow").value += ' / ';
},
buttonDotHandler : function() {
  document.getElementById("textRow").value += '.';
},
buttonCHandler : function() {
  document.getElementById("textRow").value = '';
},
memButtonHandlers : function() {
	//var mem;
	return {
		buttonMRHandler : function() {
			document.getElementById("textRow").value += mem;
		},
		buttonMCHandler : function() {
			console.log(mem);
			mem = 0;
		},
		buttonMSHandler : function() {
			mem = parseInt(document.getElementById("textRow").value, 10);
			document.getElementById("textRow").value = '';
			console.log(mem);
		},
		buttonMminusHandler : function() {
			mem -= parseInt(document.getElementById("textRow").value, 10);
			document.getElementById("textRow").value = '';
			console.log(mem);
		},
		buttonMplusHandler : function() {
			mem += parseInt(document.getElementById("textRow").value, 10);
			document.getElementById("textRow").value = '';
			console.log(mem);
		}
	}
}

} // end of Calc;
