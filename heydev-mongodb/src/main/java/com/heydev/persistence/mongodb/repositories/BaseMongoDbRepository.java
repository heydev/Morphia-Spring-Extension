package com.heydev.persistence.mongodb.repositories;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

@Component
public abstract class BaseMongoDbRepository
{
    protected static final Logger _logger = LoggerFactory.getLogger(BaseMongoDbRepository.class);
    private static Mongo _mongo;
    protected Datastore _store;
    private String uri;
    private String database;
    private String _package;
    
    protected BaseMongoDbRepository(String uri, String database, String _package)
    {
        this.uri = uri;
        this.database = database;
        this._package = _package;
    }
    
    @PostConstruct
    protected void init()
    {
        _logger.debug(String.format("Connecting to mongodb %s on instance %s.", database, uri));
        try
        {                        
            if (_mongo == null)
            {
                _mongo = new Mongo(uri);
            }
             
            Morphia morphia = new Morphia();
            morphia.mapPackage(_package);
            _store = morphia.createDatastore(_mongo, database);
            _store.ensureCaps();
            _store.ensureIndexes();
        } 
        catch (Exception ex)
        {
            _logger.error("An exception occured while bootstapping the BaseMongoDbRepository!", ex);
        }
    }
}
