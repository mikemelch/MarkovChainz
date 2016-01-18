# MarkovChainz

An exercise project aimed to train Markov Chains with your favorite artist's lyrics from genius.com

#### What is a Markov Chain? Credit: [Wiki](https://en.wikipedia.org/wiki/Markov_chain)
> A Markov chain, named after Andrey Markov, is a random process that undergoes 
> transitions from one state to another on a state space. It must possess a property that is usually characterized as 
> "memorylessness": the probability distribution of the next state depends only on the current state and not on the sequence 
> of events that preceded it. This specific kind of "memorylessness" is called the Markov property. Markov chains have many 
> applications as statistical models of real-world processes.

#### How was this built?
* Spring Boot w/ MongoDB for storing lyrics, once downloaded
* Jsoup for parsing lyrics from genius.com

#### Usage
1. search {artist name}

  > Searches for a given artist on genius.com and populates all of their songs
  
2. markov {order}

  > Creates the markov chains for the artist with the given order
  
3. generate {number of words}

  > Generates a given number of words using the previously created markov chains




#### Example

``>>>`` search taylor swift

> Found artist: Taylor Swift

> Artist already stored in database.

``>>>`` markov 2

> Created Markov model with an order = 2

``>>>`` generate 50

> It wasn't just like a record changer. The rumors are terrible and cruel. But honey most of them are true. Cause baby I did first?. ...
