# Description

A small Java library to implement the builder pattern more easily.

This uses reflection and exposes a method `GeneraicBuilder.asBuilder`
that will create a dynamic proxy to implement a Builder interface. The
idiom we support is the follwoing:

* You have a class *C* with private variables *f1*, *f2*, etc...
* You have an interface *C.Builder* with methods *setF1()*, *setF2()*, etc..
* Use this library to create a method on *C.newBuilder()* to create an instance of *C.Builder* in one line without writing any extra code.

# Usage

Suppose you have the class *Thing* with builder interface
*Thing.Builder*:

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

You can add a *newBuilder* method to create a *Thing.Builder* by
creating an instance of *com.jeffpalm.genericbuilder.GenericBuilder*:

<pre>
class Thing {
  // ....
  public static Builder newBuilder() {
    return new GenericBuilder&lt;Builder&gt;(
         new Thing(), Builder.class).asBuilder();
  }
}
</pre>

Then you can use the following to create a *Thing*:

<pre>
Thing thing = Thing.newBuilder().setFile(file).setWidth(100).build();
thing.getWidth() == 100; // true
</pre>
