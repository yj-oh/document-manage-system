package com.yjworld.docmng;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.hasSize;

public class DocumentManagementSystemTest {

	private static final String RESOURCES =
			"src" + File.separator + "main" + File.separator + "test"
					+ File.separator + "resources" + File.separator;
	private static final String INVOICE = RESOURCES + "patient.invoice";
	private static final String LETTER = RESOURCES + "patient.letter";
	private static final String REPORT = RESOURCES + "patient.report";
	private static final String XRAY = RESOURCES + "xray.jpg";

	private DocumentManagementSystem system = new DocumentManagementSystem();

	@Test
	public void shouldImportFile() throws Exception {
		system.importFile(LETTER);

		final Document document = onlyDocument();

		assertAttributeEquals(document, Attributes.PATH, LETTER);
	}

	private void assertAttributeEquals(
			final Document document,
			final String attributeName,
			final String expectedValue) {
		assertEquals("Document has the wrong value for " + attributeName,
				expectedValue,
				document.getAttribute(attributeName));
	}

	private Document onlyDocument() {
		final List<Document> documents = system.contents();
		assertThat(documents, hasSize(1));
		return documents.get(0);
	}

}
