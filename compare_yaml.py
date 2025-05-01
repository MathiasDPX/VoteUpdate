"""
Compare two YAML files to print differences in keys
like missing votes translations.

Requirements:
- PyYAML
"""

import argparse
import yaml
import os


def collect_keys(file: dict, prefix: str = "", current_depth: int = 0, max_depth: int = 1) -> list:
    keys = []
    for key, value in file.items():
        full_key = f"{prefix}{key}" if prefix else key
        keys.append(full_key)

        if isinstance(value, dict) and current_depth < max_depth:
            nested_keys = collect_keys(
                value,
                prefix=f"{full_key}.",
                current_depth=current_depth + 1,
                max_depth=max_depth
            )
            keys.extend(nested_keys)

    return keys


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Compare two YAML files to print differences in keys like missing votes translations."
    )

    parser.add_argument("--source", default="en_us.yml", type=str, help="Path to the source YAML file")
    parser.add_argument("--target", type=str, help="Path to the target YAML file", required=True)
    parser.add_argument("--depth", type=int, default=1, help="Maximum depth of the YAML file to compare")

    args = parser.parse_args()

    SOURCE = args.source
    TARGET = args.target

    RESOURCES = "src/main/resources/locales/"  # Folder where the YAML files are located
    MAX_DEPTH = args.depth  # Maximum depth of the YAML file to compare

    if os.path.exists(RESOURCES) is False:
        print(f"Resources folder {RESOURCES} does not exist.")
        exit(1)

    if os.path.exists(f"{RESOURCES}{SOURCE}") is False:
        print(f"Source file {SOURCE} does not exist.")
        exit(1)

    if os.path.exists(f"{RESOURCES}{TARGET}") is False:
        print(f"Target file {TARGET} does not exist.")
        exit(1)

    # Load the YAML files
    source = yaml.safe_load(open(f"{RESOURCES}{SOURCE}", "r", encoding="utf-8"))
    target = yaml.safe_load(open(f"{RESOURCES}{TARGET}", "r", encoding="utf-8"))

    source_keys = collect_keys(source, max_depth=MAX_DEPTH)
    target_keys = collect_keys(target, max_depth=MAX_DEPTH)

    for source_key in source_keys:
        if source_key not in target_keys:
            print(f"Missing {source_key}")
