# Crypto - Android App

This is a sample Android app that displays cryptocurrency prices and graph using Jetpack Compose UI toolkit along with Retrofit and Room libraries. 
The app follows the MVVM architecture pattern and includes a custom line chart component.

## Screenshots

[<img src="/readme/homescreen.gif" align="center"
width="200"
hspace="10" vspace="10">](/readme/homescreen.gif)

## Features

The android app lets you:
- Display current prices for Bitcoin, Ethereum, Litecoin etc.
- Display price trends using custom line chart
- Save favorite cryptocurrencies and view them in a separate screen.

## Libraries

- Jetpack Compose: A modern UI toolkit for building native Android UIs.
- Retrofit: A type-safe HTTP client for Android and Java.
- Room: A database library for Android that provides an abstraction layer over SQLite.
- MPAndroidChart: A library for creating custom charts and graphs in Android.

## Architecture

The app follows the MVVM architecture pattern. The main components are:
- View: Implemented using Jetpack Compose UI toolkit.
- ViewModel: Manages the app's UI data and state using LiveData and Coroutines.
- Repository: Acts as a single source of truth for data by communicating with the local database and remote API using Retrofit.
- Database: Implemented using Room library to store and retrieve cryptocurrency data.

## Custom Line Chart
The app includes a custom line chart component that displays real time price trends.

## Usage
To use the app, clone the repository and open the project in Android Studio. Build and run the app on an emulator or physical device.

## Project Tutorial
This project was built using the guidance of a Skillshare tutorial. If you are interested in learning how to build this app step-by-step, you can follow the tutorial on Skillshare.

During the development of this project, I found several Skillshare videos by Jitendra Pratap to be particularly helpful. These videos provided me with valuable insights and best practices for building android application using jetpack compose.
