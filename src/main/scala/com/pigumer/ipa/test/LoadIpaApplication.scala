package com.pigumer.ipa.test

import java.io.FileInputStream
import java.nio.charset.StandardCharsets
import java.util.zip.ZipInputStream

import org.bouncycastle.cms.CMSSignedData

object LoadIpaApplication extends App {

  val fileName = "test.ipa"
  val fis = new FileInputStream(fileName)
  val zip = new ZipInputStream(fis)
  Stream.continually(Option(zip.getNextEntry)).takeWhile(_.isDefined).foreach { opt ⇒
    opt.filter(entry ⇒ entry.getName.endsWith(".mobileprovision")).foreach { entry ⇒
      val bytes = Stream.continually(zip.read()).takeWhile(_ != -1).map(_.toByte).toArray
      val xml = new CMSSignedData(bytes).getSignedContent.getContent.asInstanceOf[Array[Byte]]
      println(new String(xml, StandardCharsets.UTF_8))
    }
  }
}
