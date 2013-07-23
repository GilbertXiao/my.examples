package com.shravan.velocity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.app.event.implement.EscapeXmlReference;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VMThread extends Thread {

    public VMThread(String name) {
        super(name);
    }

    public void run() {
        try {
            translate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public void translate() throws Exception {
        System.out.println("Thread name: "+this.getName());
        /* first, get and initialize an engine */
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
        /* next, get the Template */

        Template t = ve.getTemplate("templates/HelloWorld.vm");
        /* create a context and add data */
        VelocityContext context = new VelocityContext();
        MockPurchaseOrder po = new MockPurchaseOrder();
        context.put("po",po);
        context.put("name", "Wor & ld");
        context.put("CONTEXT", context);

        EventCartridge ec = new EventCartridge();
        ec.addEventHandler(new EscapeXmlReference());
        ec.attachToContext(context);

        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        /* show the World */
        write2file(writer.toString());
    }

    public void write2file(String msg) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("c:/velocity/"+this.getName());
            fos.write(msg.getBytes());
            fos.flush();
            fos.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("run as follows");
            System.out.println("java FileCopy <source> <dest>");
            return;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
