package com.jeffpalm.builder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

/**
 * Tests for {@link GenericBuilder}.
 */
public class GenericBuilderTest {

  public static class Thing {
    private File file;
    private int width;

    public File getFile() {
      return file;
    }

    public int getWidth() {
      return width;
    }

    public interface Builder extends com.jeffpalm.builder.Builder<Thing> {
      Builder setFile(File outDir);
      Builder setWidth(int width);
    }

    public static Builder newBuilder() {
      return new GenericBuilder<Builder>(new Thing(), Builder.class).asBuilder();
    }
  }

  public static class SubThing extends Thing {
    private int height;

    public interface Builder extends com.jeffpalm.builder.Builder<SubThing> {
      Builder setFile(File outDir);
      Builder setWidth(int width);
      Builder setHeight(int height);
    }

    public static SubThing.Builder newSubBuilder() {
      return new GenericBuilder<SubThing.Builder>(new SubThing(), SubThing.Builder.class)
          .asBuilder();
    }
  }

  @Test
  public void testEquals() {
    Thing.Builder builder = Thing.newBuilder();
    assertTrue(builder.equals(builder));
  }

  @Test
  public void testHashCode() {
    GenericBuilder<Thing.Builder> genericBuilder = new GenericBuilder<Thing.Builder>(new Thing(),
        Thing.Builder.class);
    Thing.Builder builder = genericBuilder.asBuilder();
    assertFalse(builder.hashCode() == genericBuilder.hashCode());
  }

  @Test
  public void testToString() {
    GenericBuilder<Thing.Builder> genericBuilder = new GenericBuilder<Thing.Builder>(new Thing(),
        Thing.Builder.class);
    Thing.Builder builder = genericBuilder.asBuilder();
    assertFalse(builder.toString().equals(genericBuilder.toString()));
  }

  @Test
  public void testAsBuilder() {
    GenericBuilder<Thing.Builder> genericBuilder = new GenericBuilder<Thing.Builder>(new Thing(),
        Thing.Builder.class);
    assertTrue(genericBuilder.asBuilder() == genericBuilder.asBuilder());
  }

  @Test
  public void testThing() {

    File file = new File(".");
    int width = 100;

    Thing thing = Thing.newBuilder().setFile(file).setWidth(width).build();

    assertEquals(file, thing.file);
    assertEquals(width, thing.width);
  }

  @Test
  public void testSubThing() {

    File file = new File(".");
    int width = 100;
    int height = 200;

    SubThing thing = SubThing.newSubBuilder().setFile(file).setWidth(width).setHeight(height)
        .build();

    assertEquals(file, thing.getFile());
    assertEquals(width, thing.getWidth());
    assertEquals(height, thing.height);
  }
}
