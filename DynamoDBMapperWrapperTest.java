import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class DynamoDBMapperWrapperTest {

    private DynamoDBMapper mapper;
    private AmazonDynamoDB client;
    private DynamoDBMapperConfig config;
    private Object toSave;
    private Object key;
    private Object loaded;
    private DynamoDBQueryExpression queryExpression;
    private DynamoDBScanExpression scanExpression;
    private PaginatedQueryList queryList;
    private PaginatedScanList scanList;
    private QueryResultPage queryResultPage;
    private ScanResultPage scanResultPage;

    private DynamoDBMapperWrapper mapperWrapper;

    @Before
    public void setUp() {
        mapper = mock(DynamoDBMapper.class);
        client = mock(AmazonDynamoDB.class);
        config = DynamoDBMapperConfig.DEFAULT;
        toSave = new Object();
        key = new Object();
        loaded = new Object();
        queryExpression = new DynamoDBQueryExpression();
        scanExpression = new DynamoDBScanExpression();
        queryList = mock(PaginatedQueryList.class);
        scanList = mock(PaginatedScanList.class);
        queryResultPage = new QueryResultPage();
        scanResultPage = new ScanResultPage();

        mapperWrapper = new DynamoDBMapperWrapper(mapper, client);
    }

    @Test
    public void update() {
        final UpdateItemRequest request = mock(UpdateItemRequest.class);

        mapperWrapper.update(request);

        verify(client).updateItem(request);
    }

    @Test
    public void saveObject() {
        final Object result = mapperWrapper.save(toSave);

        verify(mapper).save(toSave);
        assertEquals(toSave, result);
    }

    @Test
    public void saveObjectWithConfig() {
        final Object result = mapperWrapper.save(toSave, config);

        verify(mapper).save(toSave, config);
        assertEquals(toSave, result);
    }

    @Test
    public void saveObjectWithSaveExpression() {
        final DynamoDBSaveExpression expression = new DynamoDBSaveExpression();

        final Object result = mapperWrapper.save(toSave, expression);

        verify(mapper).save(toSave, expression);
        assertEquals(toSave, result);
    }

    @Test
    public void saveObjectWithConfigAndSaveExpression() {
        final DynamoDBSaveExpression expression = new DynamoDBSaveExpression();

        final Object result = mapperWrapper.save(toSave, expression, config);

        verify(mapper).save(toSave, expression, config);
        assertEquals(toSave, result);
    }

    @Test
    public void loadObject() {
        when(mapper.load(key)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(key);

        verify(mapper).load(key);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void loadObjectWithConfig() {
        when(mapper.load(key, config)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(key, config);

        verify(mapper).load(key, config);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void loadClassWithHashKey() {
        when(mapper.load(Object.class, key)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(Object.class, key);

        verify(mapper).load(Object.class, key);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void loadClassWithHashKeyAndRangeKey() {
        when(mapper.load(Object.class, key, key)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(Object.class, key, key);

        verify(mapper).load(Object.class, key, key);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void loadClassWithHashKeyAndConfig() {
        when(mapper.load(Object.class, key, config)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(Object.class, key, config);

        verify(mapper).load(Object.class, key, config);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void loadClassWithHashKeyAndRangeKeyAndConfig() {
        when(mapper.load(Object.class, key, key, config)).thenReturn(loaded);

        final Optional result = mapperWrapper.load(Object.class, key, key, config);

        verify(mapper).load(Object.class, key, key, config);
        assertEquals(Optional.ofNullable(loaded), result);
    }

    @Test
    public void queryClassWithQueryExpression() {
        when(mapper.query(Object.class, queryExpression)).thenReturn(queryList);

        final PaginatedList result = mapperWrapper.query(Object.class, queryExpression);

        verify(mapper).query(Object.class, queryExpression);
        assertEquals(queryList, result);
    }

    @Test
    public void queryClassWithQueryExpressionAndConfig() {
        when(mapper.query(Object.class, queryExpression, config)).thenReturn(queryList);

        final PaginatedList result = mapperWrapper.query(Object.class, queryExpression, config);

        verify(mapper).query(Object.class, queryExpression, config);
        assertEquals(queryList, result);
    }

    @Test
    public void queryPageWithClassAndQueryExpression() {
        when(mapper.queryPage(Object.class, queryExpression)).thenReturn(queryResultPage);

        final QueryResultPage result = mapperWrapper.queryPage(Object.class, queryExpression);

        verify(mapper).queryPage(Object.class, queryExpression);
        assertEquals(queryResultPage, result);
    }

    @Test
    public void queryPageWithClassAndQueryExpressionAndConfig() {
        when(mapper.queryPage(Object.class, queryExpression, config)).thenReturn(queryResultPage);

        final QueryResultPage result = mapperWrapper.queryPage(Object.class, queryExpression, config);

        verify(mapper).queryPage(Object.class, queryExpression, config);
        assertEquals(queryResultPage, result);
    }

    @Test
    public void scanClassWithScanExpression() {
        when(mapper.scan(Object.class, scanExpression)).thenReturn(scanList);

        final PaginatedList result = mapperWrapper.scan(Object.class, scanExpression);

        verify(mapper).scan(Object.class, scanExpression);
        assertEquals(scanList, result);
    }

    @Test
    public void scanClassWithScanExpressionAndConfig() {
        when(mapper.scan(Object.class, scanExpression, config)).thenReturn(scanList);

        final PaginatedList result = mapperWrapper.scan(Object.class, scanExpression, config);

        verify(mapper).scan(Object.class, scanExpression, config);
        assertEquals(scanList, result);
    }

    @Test
    public void scanPageWithClassAndScanExpression() {
        when(mapper.scanPage(Object.class, scanExpression)).thenReturn(scanResultPage);

        final ScanResultPage result = mapperWrapper.scanPage(Object.class, scanExpression);

        verify(mapper).scanPage(Object.class, scanExpression);
        assertEquals(scanResultPage, result);
    }

    @Test
    public void scanPageWithClassAndScanExpressionAndConfig() {
        when(mapper.scanPage(Object.class, scanExpression, config)).thenReturn(scanResultPage);

        final ScanResultPage result = mapperWrapper.scanPage(Object.class, scanExpression, config);

        verify(mapper).scanPage(Object.class, scanExpression, config);
        assertEquals(scanResultPage, result);
    }
}
