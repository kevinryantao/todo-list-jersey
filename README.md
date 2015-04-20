# todo-list-jetty
Todo list web app

To start this server follow these instructions:

step 1 :

git clone git@github.com:kevinryantao/todo-list-jetty.git

step 2 :

cd todo-list-jetty

step 3 :

mvn jetty:run


Here are the various endpoints available and sample queries:

/create

sample usage :

curl -H "Content-Type: application/json" -X POST -d '{"user":"Kevin Tao","description":"Work Out","due-date":"April 30th"}' http://localhost:8080/create


/markAsCompleted
*** Has NOT YET been implemented ***

/readAllItems

sample usage :

http://localhost:8080/readAllItems <- This gives you all items in all todo lists
http://localhost:8080/readAllItems?user=John%20Doe <- This gives you all of John Doe's items

/readIncompleteItems

sample usage : 

http://localhost:8080/readAllItems <- This gives you all items in all todo lists
http://localhost:8080/readIncompleteItems?user=John%20Doe <- This gives you all of John Doe's incomplete items
