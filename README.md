# Среда для написания программ на Verilog по TDD

## Структура проекта
name_project
  src
    lib.v
    ... // your src files
  test
    ... // your test files
    
## Содержание lib.v
```
module assertEquals(input a, b, output reg out);
	initial begin 
		#1
		if (a == b)
			assign out = 1'b1;
		else
			assign out = 1'b0;
	end
endmodule
```
