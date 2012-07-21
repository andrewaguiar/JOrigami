## JOrigami

JOrigami provides a very simple and elegant way to process text files (or any InputStream).

Find the file layout, for example with this file below:

```text
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
		teste com o componente origami  1123M0109201114591.723.569991COMPLEX
```



Create your Origami class, like this:

```java
		public class BasicOrigami {
		
			public enum OrigamiTestType {
				SIMPLE, COMPLEX, NORMAL
			}
		
			@OrigamiField(start = 1, end = 30)
			private String name;
		
			@OrigamiField(start = 31, end = 33)
			private int idade;
		
			@OrigamiField(start = 37, end = 37)
			private char sexo;
		
			@OrigamiField(start = 38, end = 49, opt = "ddMMyyyyHHmm")
			private Date dataNascimento;
		
			@OrigamiField(start = 50, end = 53)
			private double altura;
		
			@OrigamiField(start = 54, end = 57)
			private float note;
		
			@OrigamiField(start = 58, end = 60)
			private short code;
		
			@OrigamiField(start = 61, end = 61)
			private byte digit;
		
			@OrigamiField(start = 62, end = 69)
			private OrigamiTestType type;
			
			// Getters and setters...
		}
```

Each field annoted with @OrigamiField indicates the range of information for each unit of informations.

3) Now you can start the processing, in this example each 'unit of informations' is a row.

```java
		OrigamiFactory origami = OrigamiFactory.createLinesBasedFactory(BasicOrigami.class, new ConsolidatingListener<BasicOrigami>() {
			@Override
			public void process(final BasicOrigami bo) {
				// Do whatever you wanna with the just created Origami.
			}
		});
		origami.mount(new FileInputStream("tests/many_lines.txt"));
```

# Some classes

## OrigamiFactory

Execute the processing of InputStream. use its factory method.

1. createLinesBasedFactory: assumes that a '\n' separates the 'unit of informations'.
2. createSharpBasedFactory: assumes that a '#' separates the 'unit of informations'.
3. createCommaBasedFactory: assumes that a ',' separates the 'unit of informations'.
4. Contructor (OrigamiFactory) for customized separators use the default constructor.

## ConsolidatingListener
During the processing each 'unit of informations' that is cosolidated in a Origami object will pass by a 'ConsolidatingListener'.

## OrigamiField
Annotation used to mark a field to be filled with informations.

## FaultListener
During the processing each 'unit of informations' that generates a Exception in the conversion will pass by a 'FaultListener'.

## OrigamiFormatter
Used to convert from String (text file) to basic java types, can be inherited to provide more complex forms of conversion.