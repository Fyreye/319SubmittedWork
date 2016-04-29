$(document).ready(function() {
	var myLib = new Library(3, 14);
	myLib.createShelves();
	myLib.createBooks();
	myLib.populateShelves();
});

function Library(numShelves, numBooks) {
	this.numShelves = numShelves;
	this.numBooks = numBooks;
	this.rows = Math.ceil(numBooks/numShelves);
	this.shelves = [];
	this.books = [];
}

Library.prototype.createShelves = function() {
	for(var i = 0; i < this.numShelves; i++) {
		this.shelves[i] = new Shelf(i);
	}
	
	var mytable = $("<table border='2' id='bookShelf'></table>");
	var mytablehead = $('<tr></tr>');
	var mytablebody = $('<tbody></tbody>');
	
	for(var column = 1; column <= this.numShelves; column++) {
		var head_col = $('<th></th>');
		var head_text = 'Shelf ' + column;
		head_col.append(head_text);
		mytablehead.append(head_col);
	}
	mytablebody.append(mytablehead);
	 
    for(var row = 0; row < this.rows; row++) {
		var curr_row = $('<tr></tr>');

	    for(var col = 0; col < this.numShelves; col++) {
			var curr_cell = $('<td class=' + col + '></td>');
	        curr_row.append(curr_cell);
	    }
	    mytablebody.append(curr_row);
    }
    mytable.append(mytablebody);
    mytable.insertBefore($('#shelf'));
}

Library.prototype.createBooks = function() {
	for(var i = 0; i < this.numBooks; i++) {
		var status = Math.floor((Math.random() * 99));
		this.books[i] = new Book("Book" + i, status, i);
	}
	var j = 0;
	for(var k = 0; k < this.numShelves; k++) {
		for(var h = 0; h < this.rows; h++) {
			if(j<this.numBooks)
				this.shelves[k].giveBook(this.books[j++]);
		}
	}
}

Library.prototype.populateShelves = function() {
	for(var i = 0; i < this.numShelves; i++) {
		this.shelves[i].populateRows();
	}
}

function Shelf(shelfID) {
	this.shelfID = shelfID;
	this.books = [];
	this.index = 0;
}

Shelf.prototype.giveBook = function(book) {
	this.books[this.index++] = book;
}

Shelf.prototype.populateRows = function() {
	var rows = $("." + this.shelfID);
	for(var i = 0; i < this.books.length; i++) {
		var book = this.books[i];
		var text = "<p id='" + book.number + "'>" + book.title + "</p>";
		$(rows[i]).append(text);
		$("#" + book.number).click((function() {
			var j =	book;
			return function() {
				j.displayStatus();
		}})());																																																	   //	
	}
}

function Book(title, state, number) {
	this.title = title;
	this.state = state;
	this.number = number;
}

Book.prototype.displayStatus = function() {
	var statusMessage = "<b>" + this.title + " status:</b>";
	if(this.state >= 40) {
		statusMessage += "<p class='text-success'>Available</p>"
	}
	else {
		statusMessage += "<p class='text-danger'>Checked out</p>";
	}
	$("#status").html(statusMessage);
	$("#bookStatus").show();
}
