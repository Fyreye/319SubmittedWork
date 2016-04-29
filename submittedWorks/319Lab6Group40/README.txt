The code for the library works as follows:
A new Library object is created when the document is ready.  This object is created with 3 shelves and 14 books currently.
The library will automatically calculate how many books to place on each shelf based on the result of the function call Math.ceil(numBooks/numShelves).
The library then creates arrays for the books and shelves.

Several Library functions are called.  First is createShelves.  This creates several Shelf objects each with an id and an empty array of Books.  This method also creates an empy table in the document to be populated by later methods.
Next createBooks is called.  This creates a number of Book objects each with a title, status, and number.  The Books are then placed in the arrays contained within the previously created Shelf objects by use of the Shelf method giveBook.
The table is now ready to be populated using the populateShelves method.  This method calls the populateRows method for each Shelf in contained in the library. The populateRows method fills in the title of each book in the Shelf's collection
into the cells in the table below the Shelf's column heading. Finally, each cell in the table is set so that when it is clicked the displayStatus method for the Book is displayed.