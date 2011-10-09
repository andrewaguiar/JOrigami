import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import br.org.origami.ConsolidatingListener;
import br.org.origami.OrigamiFactory;

public class TestOrigami extends TestCase {

	private BasicOrigami readFromOneLine() throws IOException, FileNotFoundException {
		final List<BasicOrigami> list = new LinkedList<BasicOrigami>();

		final OrigamiFactory origami = OrigamiFactory.createLinesBasedFactory(BasicOrigami.class, new ConsolidatingListener<BasicOrigami>() {
			@Override
			public void process(final BasicOrigami bo) {
				list.add(bo);
			}
		});
		origami.mount(new FileInputStream("tests/one_line.txt"));

		final BasicOrigami bo = list.get(0);
		return bo;
	}

	public void testCouldCreateOneOrigami() throws Exception {
		final List<BasicOrigami> list = new LinkedList<BasicOrigami>();

		final OrigamiFactory origami = OrigamiFactory.createLinesBasedFactory(BasicOrigami.class, new ConsolidatingListener<BasicOrigami>() {
			@Override
			public void process(final BasicOrigami bo) {
				list.add(bo);
			}
		});
		origami.mount(new FileInputStream("tests/one_line.txt"));

		Assert.assertFalse(list.size() == 0);
		Assert.assertNotNull(list.get(0));
	}

	public void testStringValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals("teste com o componente origami", bo.getName());
	}

	public void testIntValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(1, bo.getIdade());
	}

	public void testCharValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals('M', bo.getSexo());
	}

	public void testDoubleValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(1.72D, bo.getAltura());
	}

	public void testFloatValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(3.56F, bo.getNote());
	}

	public void testShortValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(999, bo.getCode());
	}

	public void testByteValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(1, bo.getDigit());
	}

	public void testEnumValue() throws Exception {
		final BasicOrigami bo = this.readFromOneLine();
		Assert.assertEquals(BasicOrigami.OrigamiTestType.COMPLEX, bo.getType());
	}

	public void testDateValue() throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("010920111459");

		final BasicOrigami bo = this.readFromOneLine();

		Assert.assertNotNull(bo.getDataNascimento());

		final String dateFormatted = sdf.format(bo.getDataNascimento());

		Assert.assertEquals("010920111459", dateFormatted);
	}

	public void testCouldCreateOrigamisFromFile() throws Exception {
		final List<BasicOrigami> list = new LinkedList<BasicOrigami>();

		final OrigamiFactory origami = OrigamiFactory.createLinesBasedFactory(BasicOrigami.class, new ConsolidatingListener<BasicOrigami>() {
			@Override
			public void process(final BasicOrigami bo) {
				list.add(bo);
			}
		});
		origami.mount(new FileInputStream("tests/many_lines.txt"));

		Assert.assertFalse(list.size() == 0);
		Assert.assertNotNull(list.get(0));
	}
}
