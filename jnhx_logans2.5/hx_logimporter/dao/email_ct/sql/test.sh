create EXTERNAL table if not exists t_iis_source23
                                        (
                                        time string,mdate string,city string,country string,result string,opt string,source_log string,req_ip string,username string
                                        )
                                        partitioned by(unit string,project string,ana_obj string,rulegroup string,rule string)
                                        
                                        sort by (req_ip)
                                        
                                        STORED AS PARQUET
                                        location '/home/hxht_data/mlogs/';

