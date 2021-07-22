# Simple Search Engine

Simple Search Engine is a simple version of search engine which processes data from a text file and provides a way to look it up by a word or a phrase. 
It ignores cases and extra spaces, and supports complex queries containing a sequence of words and a strategy that determines how to match data.
The search is optimized by using an inverted index.

## Search Strategies

Supported search strategies: ALL, ANY and NONE.

If the strategy is ALL, the program prints lines containing all the words from the query.

If the strategy is ANY, the program prints the lines containing at least one word from the query.

If the strategy is NONE, the program prints lines that do not contain words from the query at all.

## Running

`--data <filename>`, where `filename` is the path to the file with the data, for example, `--data text.txt`.

## Resources

The [project](https://hyperskill.org/projects/66) is a part of Java Developer track in JetBrains Academy.
