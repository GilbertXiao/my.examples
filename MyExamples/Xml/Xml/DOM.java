Node
  |--> Document
  |--> Element
  |--> Attr
  |--> Comment
  |--> ProcessingInstruction
  |--> Entity
  |--> Notation
  |--> EntityReference
  |--> DocumentType
  |--> DocumentFragment
  |--> CharacterData
					|--> Text
								|--> CDATASection
------------------------------------------------
NodeList
NamedNodeMap


Node
----
String		 = nodeName
String		 = nodeValue
int				 = nodeType

	1		Element
	2		Attribute
	3		Text
	4		CDATASection
	5		EntityReference
	6		Entity
	7		ProcessingInstruction
	8		Comment
	9		Document
	10	DocumentType
	11	DocumentFragment
	12	Notation

NodeList		 = childNodes

NamedNodeMap = attributes

Node = parentNode
Node = firstChild
Node = lastChild
Node = previousSibling
Node = nextSibling
Document = ownerDocument

Document
--------
Element      = documentElement
DocumentType = docType

DOMImplementation domImpl = implementation

boolean = domImpl.hasFeature("XML","1.0")
boolean = domImpl.hasFeature("HTML","4.0")


Element
-------
String = tagName

Attr
----
String  = name
String  = value
boolean = specified

NodeList
--------
int  = length
Node = item( index )

NamedNodeMap
------------
int = length

Node = item( int index )
Node = getNamedItem( String nodeName )
Node = setNamedItem( String, Node)
Node = removeNamedItem( String )
