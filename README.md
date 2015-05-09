Project Carlos - Taxi trips - Question 3
============================================

The project attemps to answer the Question 3 of the task.


## Deploying the stream in Spring XD


Upload the modules
    
    xd:>module upload --file '...'/target/latlong-module-1.0.0.BUILD-SNAPSHOT.jar --name latlong --type processor
    xd:>module upload --file '...'/target/cleanser-module-1.0.0.BUILD-SNAPSHOT.jar --name cleanser --type processor
    xd:>module upload --file '...'/target/day.transformation-1.0-SNAPSHOT.jar --name dayTransformer --type processor

Create and deploy the stream
    
    xd:>stream create --name taxiStuff --definition "reactor-ip | cleanser | dayTransformer | latlong |  log" --deploy

From a separate termingal stream the file input into nc

    cat 'path to file'/sorted_data.csv | nc localhost 3000

To refresh modules, and stream

    xd:>stream destroy --name taxiStuff
    xd:>module delete --name processor:cleanser
    xd:>module delete --name processor:dayTransformer
    xd:>module delete --name processor:latlong
    
