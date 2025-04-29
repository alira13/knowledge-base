import re
import matplotlib.pyplot as plt
from datetime import datetime, timedelta

def parse_discharge_history(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    timestamps = []
    levels = []

    base_time = None

    for line in lines:
        if 'Discharge step' in line and 'level=' in line:
            # Пример строки: Discharge step #1: duration=2m15s level=97
            level_match = re.search(r'level=(\d+)', line)
            time_match = re.search(r'duration=([\dhms]+)', line)

            if level_match and time_match:
                level = int(level_match.group(1))
                duration_str = time_match.group(1)

                # Переводим duration в timedelta
                time_delta = parse_duration(duration_str)

                if not base_time:
                    base_time = datetime.now()

                current_time = base_time + time_delta

                timestamps.append(current_time)
                levels.append(level)

    return timestamps, levels


def parse_duration(duration_str):
    seconds = 0
    matches = re.findall(r'(\d+)([hms])', duration_str)
    for value, unit in matches:
        if unit == 'h':
            seconds += int(value) * 3600
        elif unit == 'm':
            seconds += int(value) * 60
        elif unit == 's':
            seconds += int(value)
    return timedelta(seconds=seconds)


def plot_discharge(timestamps, levels):
    plt.figure(figsize=(10, 5))
    plt.plot(timestamps, levels, marker='o', linestyle='-')
    plt.title('Battery Discharge Over Time')
    plt.xlabel('Time')
    plt.ylabel('Battery Level (%)')
    plt.grid(True)
    plt.gca().invert_yaxis()  # Чтобы заряд шел сверху вниз
    plt.tight_layout()
    plt.show()


# Использование
file_path = 'batterystats.txt'
timestamps, levels = parse_discharge_history(file_path)
plot_discharge(timestamps, levels)
