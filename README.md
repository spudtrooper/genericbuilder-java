* Description

A small Java library for using the builder pattern.

* Usage

Suppose you have the class <code>Thing</code> with builder 
interface <code>Thing.Builder</code>:

<pre>
class Thing {
  private File file;
  private int width;
  private Thing() {}
  interface Builder extends com.jeffpalm.buider.Builder {
    Builder setFile(File file);
    Builder setWidth(int width);
  }
}
</pre>

You can add a 'newBuilder' method to create a <code>Thing.Builder</code>
by creating an instance  of 
<code>com.jeffpalm.genericbuilder.GenericBuilder</code>:

<pre>
class Thing {
  // ....
  public static Builder newBuilder() {
    return new GenericBuilder<Builder>(
         new Thing(), Builder.class).asBuilder();
  }
}
</pre>

Then you can use the following to create a <code>Thing</code>:

<pre>
Thing thing = Thing.newBuilder().setFile(file).setWidth(100).build();
thing.getWidth() == 100; // true
</pre>
