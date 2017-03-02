ava.lang">Object</A> <B>getRenderingHint</B>(<A HREF="../../java/awt/RenderingHints.Key.html" title="class in java.awt">RenderingHints.Key</A>&nbsp;hintKey)</PRE>
<DL>
<DD>Returns the value of a single preference for the rendering algorithms.
 Hint categories include controls for rendering quality and overall 
 time/quality trade-off in the rendering process.  Refer to the
 <code>RenderingHints</code> class for definitions of some common
 keys and values.
<P>
<DD><DL>
<DT><B>Parameters:</B><DD><CODE>hintKey</CODE> - the key corresponding to the hint to get.
<DT><B>Returns:</B><DD>an object representing the value for the specified hint key.
 Some of the keys and their associated values are defined in the
 <code>RenderingHints</code> class.<DT><B>See Also:</B><DD><A HREF="../../java/awt/RenderingHints.html" title="class in java.awt"><CODE>RenderingHints</CODE></A>, 
<A HREF="../../java/awt/Graphics2D.html#setRenderingHint(java.awt.RenderingHints.Key, java.lang.Object)"><CODE>setRenderingHint(RenderingHints.Key, Object)</CODE></A></DL>
</DD>
</DL>
<HR>

<A NAME="setRenderingHints(java.util.Map)"><!-- --></A><H3>
setRenderingHints</H3>
<PRE>
public abstract void <B>setRenderingHints</B>(<A HREF="../../java/util/Map.html" title="interface in java.util">Map</A>&lt;?,?&gt;&nbsp;hints)</PRE>
<DL>
<DD>Replaces the values of all preferences for the rendering
 algorithms with the specified <code>hints</code>.
 The existing values for all rendering hints are discarded and
 the new set of known hints and values are initialized from the
 specified <A HREF="../../java/util/Map.html" title="interface in java.util"><CODE>Map</CODE></A> object.
 Hint categories include controls for rendering quality and
 overall time/quality trade-off in the rendering process.
 Refer to the <code>RenderingHints</code> class for definitions of
 some common keys and values.
<P>
<DD><DL>
<DT><B>Parameters:</B><DD><CODE>hints</CODE> - the rendering hints to be set<DT><B>See Also:</B><DD><A HREF="../../java/awt/Graphics2D.html#getRenderingHints()"><CODE>getRenderingHints()</CODE></A>, 
<A HREF="../../java/awt/RenderingHints.html" title="class in java.awt"><CODE>RenderingHints</CODE></A></DL>
</DD>
</DL>
<HR>

<A NAME="addRenderingHints(jav