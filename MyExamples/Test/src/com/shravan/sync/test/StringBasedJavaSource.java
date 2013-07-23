package com.shravan.sync.test;

import java.net.URI;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

public class StringBasedJavaSource extends SimpleJavaFileObject
{
  final String code;

  StringBasedJavaSource(String name, String code)
  {
    super(URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);

    this.code = code;
  }

  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return this.code;
  }
}