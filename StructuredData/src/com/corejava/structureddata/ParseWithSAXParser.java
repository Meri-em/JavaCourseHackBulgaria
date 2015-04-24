package com.corejava.structureddata;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseWithSAXParser {

	public static void main(String argv[]) {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean titleName = false;
				boolean nsName = false;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("title")) {
						titleName = true;
					}

					if (qName.equalsIgnoreCase("ns")) {
						nsName = true;
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					System.out.println("End Element :" + qName);

				}
				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (titleName) {
						System.out.println("PAGETITLE: "
								+ new String(ch, start, length));
						titleName = false;
					}

					if (nsName) {
						System.out.println("NS: "
								+ new String(ch, start, length));
						nsName = false;
					}
				}
			};

			saxParser.parse("xmlInShort.xml", handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}