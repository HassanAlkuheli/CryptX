#!/bin/bash
cd F:\LearningJourney\LearningKotlin\LearningKMP\crypto_wallet
echo "Starting build..."
./gradlew :androidApp:app:assembleDebug -x lint --stacktrace
echo "Build complete!"
ls -la androidApp/app/build/outputs/apk/debug/

