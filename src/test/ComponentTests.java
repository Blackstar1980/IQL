package test;

import org.junit.jupiter.api.Test;

public class ComponentTests {
	@Test void tab01() {
		TestHelper.arrgumentException("""
					'Dialog Title' Single('dialog description')
					'First Tab:' Tab{
					name 'Name:' String('John')
					}
					""",
					"There must be at least 2 tabs");
	}
	
	@Test void tab02() {
		TestHelper.checkParseError("""
				'Dialog Title' Single('dialog description')
				'First Tab:' Tab{}
				""");
	}
	
	@Test void tab03() {
		TestHelper.checkParseError("""
			'Dialog Title' Single('dialog description')
			'First Tab:' Taab{
			name 'Name:' String('John')
			}
			""");
	}
	
	@Test void tab04() {
		TestHelper.arrgumentException("""
				'Dialog Title' Single('dialog description')
				'First Tab:' Tab{
				name 'Name:' String('John')
				}
				""",
				"""
				There must be at least 2 tabs\
				""");
	}
	
	@Test void tab05() {
		TestHelper.checkAst("""
				'Dialog Title' Single('dialog description')
				'First Tab:' Tab{
				name 'Name:' String('John')
				}
				'Second Tab:' Tab{
					'new group' Group {
						age 'Age' Integer
				}
				surname 'Surname:' String('Doe')
				}
				""",
				"""
				Query[dialog=Single[title=Dialog Title, \
				description=dialog description, constraints=[]], \
				containers=[Tab [title=First Tab:, \
				containers=[String [name=name, title=Name:, defVal=John, constraints=[]]]], \
				Tab [title=Second Tab:, containers=[Group [title=new group, \
				components=[Integer [name=age, title=Age, defVal=null, \
				constraints=[]]]], String [name=surname, title=Surname:, \
				defVal=Doe, constraints=[]]]]]]\
				""");
	}
	
	@Test void group01() {
		TestHelper.checkAst("""
				'Dialog Title' Single('dialog description')
				'First Group:' Group{
					name 'Name:' String('John')
				}
				""",
				"""
				Query[dialog=Single[title=Dialog Title, \
				description=dialog description, constraints=[]], \
				containers=[Group [title=First Group:, \
				components=[String [name=name, title=Name:, \
				defVal=John, constraints=[]]]]]]\
				""");
	}
	
	@Test void group02() {
		TestHelper.checkParseError("""
				'Dialog Title' Single('dialog description')
				'First Group:' Group{}
				}
				""");
	}
	
	@Test void group03() {
		TestHelper.checkParseError("""
				'Dialog Title' Single('dialog description')
				'First Group:' GroupY{
					name 'Name:' String('John')
				}
				""");
	}
		
	@Test void comp01() {
		TestHelper.arrgumentException("""
				'Single Dialog' Single('single my description')
				name 'Name:' String('John')
				name 'Second Name:' String('Doe')
				""",
				"Components names must be unique. 'name', is appear more then once.");
	}
	
	@Test void comp02() {
		TestHelper.arrgumentException("""
				'Single Dialog' Single('single my description')
				'first group' Group{
				name 'Name:' String('John')
				}
				'second group' Group{
				name 'Second Name:' String('Doe')
				}
				""",
				"Components names must be unique. 'name', is appear more then once.");
	}
	
	@Test void comp03() {
		TestHelper.arrgumentException("""
				'Single Dialog' Single('single my description')
				'first Tab' Tab{
				name 'Name:' String('John')
				}
				'second Tab' Tab{
				name 'Second Name:' String('Doe')
				}
				""",
				"Components names must be unique. 'name', is appear more then once.");
	}
	
	@Test void comp04() {
		TestHelper.arrgumentException("""
			'Single Dialog' Single('single my description')
			name 'Name:' String('John') {max=1 max=6}
			""",
			"'max' constraint repeat more then once");
	}
	
	@Test void comp05() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			name 'Name:' String('John') {min=1 max=6 style=inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[String [name=name, title=Name:, defVal=John, \
			constraints=[MinCon[value=1.0], MaxCon[value=6.0], Inline]]]]\
			""");
	}
	
	@Test void comp06() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			cats 'Have cats?' Boolean('true')
			dogs 'Have dogs?' Boolean('false')
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Boolean [name=cats, title=Have cats?, \
			defVal=true, constraints=[]], Boolean [name=dogs, \
			title=Have dogs?, defVal=false, constraints=[]]]]\
			""");
	}
	
	@Test void comp07() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			cats 'Have cats?' Boolean
			dogs 'Have dogs?' Boolean
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Boolean [name=cats, title=Have cats?, \
			defVal=, constraints=[]], Boolean [name=dogs, \
			title=Have dogs?, defVal=, constraints=[]]]]\
			""");
	}
	
	@Test void comp08() {
		TestHelper.arrgumentException("""
			'Single Dialog' Single('single my description')
			dogs 'Have dogs?' Boolean('yes')
			""",
			"Boolean default value must be only 'true' or 'false'");
	}
	
	@Test void comp09() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			cats 'Have cats?' String('My default value')
			{max=4 min=2 required= true holder='my holder' style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[String [name=cats, title=Have cats?, defVal=My default value, \
			constraints=[MaxCon[value=4.0], MinCon[value=2.0], \
			Required, HolderCon[value=my holder], Inline]]]]\
			""");
	}
	
	@Test void comp10() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			cats 'Have cats?' TextArea('My default value')
			{max=4 min=2 required= true holder='my holder' style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[TextArea [name=cats, title=Have cats?, defVal=My default value, \
			constraints=[MaxCon[value=4.0], MinCon[value=2.0], \
			Required, HolderCon[value=my holder], Inline]]]]\
			""");
	}
	
	@Test void comp11() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			password 'Password:' Password('My default value')
			{max=8 min=2 required= true holder='my holder' style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Password [name=password, title=Password:, defVal=My default value, \
			constraints=[MaxCon[value=8.0], MinCon[value=2.0], \
			Required, HolderCon[value=my holder], Inline]]]]\
			""");
	}

	// TODO change style by removing the style key word
	@Test void comp12() { 
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			phone 'Phone:' Integer('988643')
			{max=8 min=2 required= true holder='87654' style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Integer [name=phone, title=Phone:, defVal=988643, \
			constraints=[MaxCon[value=8.0], MinCon[value=2.0], \
			Required, HolderCon[value=87654], Inline]]]]\
			""");
	}
	
	@Test void comp13() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			height 'Height:' Decimal('1.45')
			{max=8 min=2 required= true holder='enter your height' style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Decimal [name=height, title=Height:, defVal=1.45, \
			constraints=[MaxCon[value=8.0], MinCon[value=2.0], \
			Required, HolderCon[value=enter your height], Inline]]]]\
			""");
	}
	
	@Test void comp14() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			height 'Height:' Slider('-5, 5')
			{majorTicks=3 minorTicks=1 style= inline}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Slider [name=height, title=Height:, minVal=-5, \
			maxVal=5, defVal=-5, \
			constraints=[MajorTicksCon[value=3], \
			MinorTicksCon[value=1], Inline]]]]\
			""");
	}
	
	@Test void comp15() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			height 'Height:' Slider('0, 5, 1')
			{majorTicks=3 minorTicks=1 style= inline}
			""","""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[Slider [name=height, title=Height:, \
			minVal=0, maxVal=5, defVal=1, \
			constraints=[MajorTicksCon[value=3], \
			MinorTicksCon[value=1], Inline]]]]\
			""");
	}
	
	@Test void comp16() {
		TestHelper.numberException("""
			'Single Dialog' Single('single my description')
			height 'Height:' Slider('0.1, 5, 1')
			{majorTicks=3 minorTicks=1 style= inline}
			""",
			"Invalid Slider default values");
	}
	
	@Test void comp17() {
		TestHelper.checkParseError("""
			'Single Dialog' Single('single my description')
			height 'Height:' Slider('1, 5, 1')
			{majorTicks=3 minorTicks=1.3 style= inline}
			""");
	}
	
	@Test void comp18() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			height 'Height:' SingleOpt('Red|Blue|Green')
			{selected='Red' required=true style= inlineList}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[SingleOpt [name=height, title=Height:, options=[Red, Blue, Green], \
			constraints=[SelectedCon[value='Red'], Required, InlineList]]]]\
			""");
	}
	
	@Test void comp19() {
		TestHelper.checkAst("""
			'Single Dialog' Single('single my description')
			height 'Height:' MultiOpt('Red|Blue|Green')
			{selected='Red|Blue' style= inlineList}
			""",
			"""
			Query[dialog=Single[title=Single Dialog, \
			description=single my description, constraints=[]], \
			containers=[MultiOpt [name=height, title=Height:, \
			options=[Red, Blue, Green], constraints=[SelectedCon[value='Red|Blue'], InlineList]]]]\
			""");
	}
}
