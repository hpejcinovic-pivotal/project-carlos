Project Carlos - Taxi trips - Question 3
============================================

The project attemps to answer the Question 3 of the task.


## Deploying the stream in Spring XD


Upload the modules
    
    xd:>module upload --file '...'/target/latlong-module-1.0.0.BUILD-SNAPSHOT.jar --name latlong --type processor
    xd:>module upload --file '...'/target/cleanser-module-1.0.0.BUILD-SNAPSHOT.jar --name cleanser --type processor
    xd:>module upload --file '...'/target/day.transformation-1.0-SNAPSHOT.jar --name dayTransformer --type processor

Create and deploy the stream
    
    xd:>stream create --name taxiStuff --definition "reactor-ip | cleanser | latlong | dayTransformer | hdfs --idleTimeout=10000 --fsUri=hdfs://name-node-ip:8020" --deploy

From a separate termingal stream the file input into nc

    cat 'path to file'/sorted_data.csv | nc localhost 3000


Define external table in HAWQ to point where the Spring-XD produced result
        
        CREATE EXTERNAL TABLE taxirides(dayOfWeek varchar, pickup_datetime varchar, dropoff_datetime varchar, pickup_Cell varchar, dropoff_Cell varchar)    
        LOCATION ('pxf://192.168.177.145:50070/xd/taxiStuff/*.txt?profile=HdfsTextSimple') FORMAT 'CSV'
        LOG ERRORS INTO test_err SEGMENT REJECT LIMIT 10;


Run the query in HAWQ
    
    select dayOfWeek, pickup_Cell, dropoff_Cell, count
    from
    (
	select 
		dayOfWeek,
		pickup_Cell,
		dropoff_Cell,
		count(*) as count,
		row_number() over
		(
			partition by dayOfWeek
			order by count(*) desc
		) as rank
	        from taxirides
	        group by pickup_Cell,
	        dropoff_Cell,
	        dayOfWeek
            ) t
    where rank <=10
