Project Carlos - Taxi trips - Question 3
============================================

The project attemps to answer the Question 3 of the task.


## Deploying the stream in Spring XD

Define the following variables

    export LATLONG='path to where latlong module'
    export CLEANSER='path to the cleanser module'
    export DAY_TRANSFORMER='path to day transformer module'
    
Upload the modules
    
    xd:>module upload --file $LATLONG/target/latlong-module-1.0.0.BUILD-SNAPSHOT.jar --name latlong --type processor
    xd:>module upload --file $CLEANSER/target/cleanser-module-1.0.0.BUILD-SNAPSHOT.jar --name cleanser --type processor
    xd:>module upload --file $DAY_TRANSFORMER/target/day.transformation-1.0-SNAPSHOT.jar --name dayTransformer --type processor

Create and deploy the stream
    
    xd:>stream create --name taxiStuff --definition "reactor-ip | cleanser | dayTransformer | latlong |  log" --deploy

From a separate termingal stream the file input into nc

    cat 'path to file'/sorted_data.csv | nc localhost 3000


