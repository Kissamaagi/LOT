## Last Oope Task (L.O.T.)

Last Oope Task (shortened to L.O.T.) is a text document storing and sorting program. It was made
as an assignment for object oriented programming course (Olio ohjelmoinnin perusteet 2) in spring 2020 at Tampere University. 

#### Functionality 

The program takes in a txt file containing documents, one document by row and the document attributes separated by "///". The document collections can be of news or jokes, but not the two at the same time. There is no need to specify which types of documents you are using when starting the program, that is determined automatically. It is possible to add and remove documents in the program. However it is not possible to add a duplicate of existing documents. 

The documents can all be printed, or a specific document can be printed by id. It is possible to search documents containing specific words as well. It is also possible to pretty print documents or one document with a specified row length. 

You can also remove any characters from the documents with the polish command. Freqs can be used to count the frequencies of words in the collection or a specific document. 

By default, the documents are in the order they are given in the txt file. However with the sort function the documents can be reordered by type, date or id. 

#### Structure

The program can be compiled with javac on Oope2HT.java. Any interfaces on the apulaiset folder is made by the course teacher and not by me. The folder dokumentit contains classes for the documents, news or jokes. Kokoelma is the class for a collection of documents, that uses OmaLista, a version of LinkedList that lets you compare the items on it. 

#### Extra thoughts

Some of the functionality in the program is not made in the best possible way. The biggest example being the 
pretty printing function (tulostaRivittain) is in a very spaghetti state, and would be hundred times better if the spaces were handled differently (added before the word if needed, instead of after). Also all of the comments in the code are in finnish, since that was required of the course. 
I most likely will not go back to fix the spaghetti code at some parts or translate the comments to finnish, as I like doing other projects more than revisiting old coursework. Especially as I already got the best grade possible for this course. 