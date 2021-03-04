# Lexical Analysis

[![Shields.io](https://img.shields.io/badge/type-college%20project-orange?style=flat)](http://shields.io/)

Lexical analysis is the first phase of a compiler. It takes the modified source code from language preprocessors that are written in the form of sentences. The lexical analyzer breaks these syntaxes into a series of tokens, by removing any whitespace or comments in the source code.


This project is lexical analyzer implemented using java and regex. It takes input file and then generates the output. This project was made for **(Compiler - Forth Year)** in my college.

## Input Example
```
float x, y;
int o, l, a;
o = l = 5;
a = 6;
// xxxxxxxxx
   //
if ( o > l ) {
    a = o - l;
    y = x - 2.0;
} else {
    x = y + 6.0;
    l = o + a;
}
/* xx */
```
## Screenshot
#### Output
![Alt text](https://drive.google.com/uc?id=17LaYNfc2VIaQI6RBHMKcathsnszQcSXY "Screenshot")

## License
This project is available under the terms of [MIT License](https://choosealicense.com/licenses/mit/)
