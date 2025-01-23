#!/bin/bash

# Get all remote branches
branches=$(git branch -r | grep -v '\->' | sed 's/origin\///')

for branch in $branches; do
    echo "Processing branch $branch..."

    # Checkout the branch, creating a new local branch if necessary
    git checkout -B "$branch" "origin/$branch"

    # Overwrite package-lock.json with the remote version
    echo "Overwriting package-lock.json with the remote version for $branch..."
    git checkout "origin/$branch" -- IzvorniKod/frontend/package-lock.json || true

    # Pull the latest changes from the remote
    echo "Pulling latest changes for $branch..."
    git reset --hard "origin/$branch"
done

echo "All branches updated successfully!"
