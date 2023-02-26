package transactionDB;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Demo {

    KeyValueStore db;

    @Before
    public void init() {
        db = new KeyValueStore();
        db.set(1, "1");
        db.set(2, "2");
    }

    @Test
    public void testSimple(){
        Assert.assertEquals("1", db.get(1));
        Assert.assertEquals("2", db.get(2));
        db.unSet(1);
        Assert.assertEquals(null, db.get(1));
    }

    @Test
    public void testSimpleTrans_commit(){
        db.begin();
        Assert.assertEquals("1", db.get(1));
        db.unSet(1);
        Assert.assertEquals(null, db.get(1));
        db.commit();
        Assert.assertEquals(null, db.get(1));
    }

    @Test
    public void testSimpleTrans_rollBack(){
        db.begin();
        Assert.assertEquals("1", db.get(1));
        db.unSet(1);
        Assert.assertEquals(null, db.get(1));
        db.rollback();
        Assert.assertEquals("1", db.get(1));
    }

    @Test
    public void testNestedTrans(){
        db.begin();
        Assert.assertEquals("1", db.get(1));
        db.unSet(1);
        Assert.assertEquals(null, db.get(1));
        db.begin();
        Assert.assertEquals(null, db.get(1));
        db.set(1, "new 1");
        Assert.assertEquals("new 1", db.get(1));
        db.rollback();
        Assert.assertEquals(null, db.get(1));
        db.rollback();
        Assert.assertEquals("1", db.get(1));
    }
}
